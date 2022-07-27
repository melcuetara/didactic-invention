package com.proj.example.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.proj.example.model.Ingredient;
import com.proj.example.model.Meal;
import com.proj.example.model.Plan;
import com.proj.example.model.Response;
import com.proj.example.utility.Admin;

import javassist.expr.NewArray;

public class SearchMeal extends ActionSupport implements SessionAware{
    
    private final String APP_ID = "24f1a656";
    private final String APP_KEY = "453fc9e84106d91636c935b5857382c2";

    private String imageUrl;
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    private ArrayList<Meal> meals = new ArrayList<>();
    private int planId;
    private Plan plan = new Plan();
    public Plan getPlan() {
        return plan;
    }
    public void setPlan(Plan plan) {
        this.plan = plan;
    }
    private static Map<String, Object> userSession = ActionContext.getContext().getSession();
    private Response response;
    private String isTrue = "false";
    private double caloriesMin;
    private double caloriesMax;
    private String mealId;
    private String keyword;
    private String selectedMeal = "";
    private String selectedCuisine = "";
    private String baseUrl;
    private String mealName = "";
    private double mealCalories;
    private String[] ingredientLines;
    
    public String getMealName() {
        return mealName;
    }
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }
    private String[] mealTypes = {"Breakfast", "Dinner", "Lunch", "Snack", "Teatime"};
    private String[] cuisineTypes = {"American", "Asian", "British", "Carribean", "Central Europe", 
                                        "Chinese", "Eastern Europe", "French", "Indian", "Italian", 
                                        "Japanese", "Kosher", "Mediterranean", "Mexican", "Middle Eastern", 
                                        "Nordic", "South American", "South East Asian"};                                   
    
    public Response getResponse() {
        return response;
    }
    public void setResponse(Response response) {
        this.response = response;
    }
    public String getAPP_ID() {
        return APP_ID;
    }
    public String getAPP_KEY() {
        return APP_KEY;
    }
    public String getBaseUrl() {
        return baseUrl;
    }
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    public double getCaloriesMin() {
        return caloriesMin;
    }
    public void setCaloriesMin(double caloriesMin) {
        this.caloriesMin = caloriesMin;
    }
    public double getCaloriesMax() {
        return caloriesMax;
    }
    public void setCaloriesMax(double caloriesMax) {
        this.caloriesMax = caloriesMax;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getSelectedMeal() {
        return selectedMeal;
    }
    public void setSelectedMeal(String selectedMeal) {
        this.selectedMeal = selectedMeal;
    }
    public String getSelectedCuisine() {
        return selectedCuisine;
    }
    public void setSelectedCuisine(String selectedCuisine) {
        this.selectedCuisine = selectedCuisine;
    }
    public String[] getMealTypes() {
        return mealTypes;
    }
    public void setMealTypes(String[] mealTypes) {
        this.mealTypes = mealTypes;
    }
    public String[] getCuisineTypes() {
        return cuisineTypes;
    }
    public void setCuisineTypes(String[] cuisineTypes) {
        this.cuisineTypes = cuisineTypes;
    } 
    public String getIsTrue() {
        return isTrue;
    }
    public void setIsTrue(String isTrue) {
        this.isTrue = isTrue;
    }

    public static Map<String, Object> getUserSession() {
        return userSession;
    }
    public static void setUserSession(Map<String, Object> userSession) {
        SearchMeal.userSession = userSession;
    }
    public String execute() throws Exception {
        populateList();
        try {
            System.out.println("here");
            if (getKeyword() == null) {
                userSession.put("planId", planId);
                System.out.println("here 2");
                System.out.println(planId);
                return ERROR;
            }
            if (getMealId() != null) {
                addMeal();
            }
            URL url = new URL(generateUrl());
            setBaseUrl(generateUrl());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
 
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            
            while ((output = br.readLine()) != null) {
                ObjectMapper mapper = new ObjectMapper();
                response = mapper.readValue(output, Response.class);
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    } 

    public String generateUrl() {
        String urlStr = "https://api.edamam.com/api/recipes/v2?type=public&q=" + getKeyword() + 
            "&app_id=" + getAPP_ID() + "&app_key=" + getAPP_KEY();
        if (!getSelectedCuisine().isEmpty()) {
            urlStr += "&cuisineType=" + getSelectedCuisine();
        }
        if (!getSelectedMeal().isEmpty()) {
            urlStr += "&mealType=" + getSelectedMeal();
        }
        if (getCaloriesMin() < getCaloriesMax() || (getCaloriesMin() != 0 && getCaloriesMax() != 0)) {
            setIsTrue("true");
            urlStr += "&calories=" + getCaloriesMin() + "-" + getCaloriesMax();
        }
        urlStr += "&random=true";
        return urlStr;
    }

    public String addMeal() throws Exception {
        System.out.println(planId);
        Connection conn = Admin.conn();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO meal(plan_id, name, calories) VALUES(?, ? ,?)", Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = null;
        try {
            ps.setInt(1, Integer.parseInt("" + userSession.get("planId")));
            ps.setString(2, getMealName());
            ps.setDouble(3, getMealCalories());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                addMealIngredients(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        getMealId(); 
        System.out.println(ingredientLines.length);
        return SUCCESS;
    }
    private void addMealIngredients(int meal_id) throws Exception {
        System.out.println(planId);
        Connection conn = Admin.conn();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO ingredient(meal_id, name) VALUES(?, ?)");
        try {
            for (String ingredientName: ingredientLines) {
                ps.setInt(1, meal_id);
                ps.setString(2, ingredientName);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Admin.closeObj(conn, ps, null);
        }
    }
    public String getMealId() {
        return mealId;
    }
    public void setMealId(String mealId) {
        this.mealId = mealId;
    }
    @Override
    public void setSession(Map<String, Object> session) {
        userSession = session;
    }
    public Double getMealCalories() {
        return mealCalories;
    }
    public void setMealCalories(Double mealCalories) {
        this.mealCalories = mealCalories;
    }
    public int getPlanId() {
        return planId;
    }
    public void setPlanId(int planId) {
        this.planId = planId;
    }
    
    public void populateList() throws Exception {
        Connection conn = Admin.conn();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM meal WHERE plan_id = ?");
        ResultSet rs = null;
        try {
            ps.setInt(1, planId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Meal meal = new Meal();
                meal.setId(rs.getInt(1));
                meal.setPlan_id(rs.getInt(2));
                meal.setName(rs.getString(3));
                meal.setCalories(rs.getDouble(4));
                meals.add(meal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Admin.closeObj(conn, ps, rs);
        }
    }
    public ArrayList<Meal> getMeals() {
        return meals;
    }
    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }
    public void setMealCalories(double mealCalories) {
        this.mealCalories = mealCalories;
    }
    public String[] getIngredientLines() {
        return ingredientLines;
    }
    public void setIngredientLines(String[] ingredientLines) {
        this.ingredientLines = ingredientLines;
    }
    
}
