package com.proj.example.model;

import java.util.ArrayList;

public class Meal {
    
    private int id;
    private int plan_id;
    private String name;
    private double calories;
    private String cuisine_type;
    private String meal_type;
    private String image_url;
    private ArrayList<String> ingredients;
    public ArrayList<String> getIngredients() {
        return ingredients;
    }
    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }
    public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPlan_id() {
        return plan_id;
    }
    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getCalories() {
        return calories;
    }
    public void setCalories(double calories) {
        this.calories = calories;
    }
    public String getCuisine_type() {
        return cuisine_type;
    }
    public void setCuisine_type(String cuisine_type) {
        this.cuisine_type = cuisine_type;
    }
    public String getMeal_type() {
        return meal_type;
    }
    public void setMeal_type(String meal_type) {
        this.meal_type = meal_type;
    }

    
}
