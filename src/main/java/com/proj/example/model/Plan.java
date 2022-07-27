package com.proj.example.model;

import java.sql.Date;

public class Plan {
    
    private int id;
    private int plan_id;
    private String name;
    private double max_calories;
    private Date date;
    private String description;
    private double current_calories;
    
    public double getCurrent_calories() {
        return current_calories;
    }
    public void setCurrent_calories(double current_calories) {
        this.current_calories = current_calories;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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
    public double getMax_calories() {
        return max_calories;
    }
    public void setMax_calories(double max_calories) {
        this.max_calories = max_calories;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    
}
