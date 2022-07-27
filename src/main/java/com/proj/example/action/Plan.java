package com.proj.example.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.proj.example.model.Meal;
import com.proj.example.utility.Admin;

public class Plan extends ActionSupport implements SessionAware{
    
    private static Map<String, Object> userSession = ActionContext.getContext().getSession();
    private ArrayList<Meal> meals = new ArrayList<>();
    private ArrayList<String> ingredients = new ArrayList<>();
    private Meal meal;
    private int mealId;
    private int planId;
    private double totalCalories;

    public ArrayList<String> getIngredients() {
        return ingredients;
    }
    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }
    public int getMealId() {
        return mealId;
    }
    public void setMealId(int mealId) {
        this.mealId = mealId;
    }
    public String removePlan() throws Exception {

        int i = 0;
        Connection conn = Admin.conn();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM plan WHERE id = ?");

        try {
            ps.setInt(1, planId);
            i = ps.executeUpdate();
            if (i != 0) {
                return SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Admin.closeObj(conn, ps, null);
        }
        return ERROR;
    }
    public String execute() throws Exception {
        Connection conn = Admin.conn();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM meal WHERE plan_id = ?");
        ResultSet rs = null;
        try {
            totalCalories = 0;
            if (userSession.get("planId") != null) {
                planId = Integer.parseInt("" + userSession.get("planId"));
                userSession.remove("planId");
            }
            ps.setInt(1, planId);
            rs = ps.executeQuery();
            while (rs.next()) {
                meal = new Meal();
                meal.setId(rs.getInt(1));
                setMealId(rs.getInt(1));
                meal.setPlan_id(rs.getInt(2));
                meal.setName(rs.getString(3));
                meal.setCalories(rs.getDouble(4));
                
                meals.add(meal);
                setTotalCalories(rs.getDouble(4) + getTotalCalories());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Admin.closeObj(conn, ps, rs);
            populateIngredientsList();
        }
        return SUCCESS;
    }

    private void populateIngredientsList() throws Exception {
        Connection conn = Admin.conn();
        PreparedStatement ps = conn.prepareStatement("SELECT name FROM ingredient WHERE meal_id = ?");
        ResultSet rs = null;
        try {
            ps.setInt(1, mealId);
            rs = ps.executeQuery();
            while (rs.next()) {
                ingredients.add(rs.getString(1));
            }
            System.out.println("Size: " + ingredients.size());
            meal.setIngredients(ingredients);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Admin.closeObj(conn, ps, rs);
        }

    }

    public String removeMeal() throws Exception {

        int i = 0;
        Connection conn = Admin.conn();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM ingredient WHERE meal_id = ?");
        try {
            ps.setInt(1, mealId);
            i = ps.executeUpdate();
            if (i != 0) {
                return SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Admin.closeObj(conn, ps, null);
            removeMealRow();
        }
        return ERROR;
    }

    private void removeMealRow() throws Exception {
        Connection conn = Admin.conn();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM meal WHERE plan_id = ?");
        try {
            ps.setInt(1, planId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Admin.closeObj(conn, ps, null);
        }
    }
    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        // TODO Auto-generated method stub
        userSession = session;
    }

    public static Map<String, Object> getUserSession() {
        return userSession;
    }

    public static void setUserSession(Map<String, Object> userSession) {
        Plan.userSession = userSession;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }


    
}
