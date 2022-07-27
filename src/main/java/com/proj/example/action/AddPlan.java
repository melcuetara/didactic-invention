package com.proj.example.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.proj.example.utility.Admin;

public class AddPlan extends ActionSupport implements SessionAware {

    private static Map<String, Object> userSession = ActionContext.getContext().getSession();
    
    @Override
    public void setSession(Map<String, Object> session) {
       userSession = session;
        
    }

    private String planDate;
    private double maxCalories;
    private String planName;
    private String description;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public double getMaxCalories() {
        return maxCalories;
    }

    public void setMaxCalories(double maxCalories) {
        this.maxCalories = maxCalories;
    }


    public String execute() throws Exception {
        
        if (getMaxCalories() != 0 && isValid(getPlanDate()) && isValid(getPlanName())) {
            if (Admin.savePlan(this) == 1) {
                return SUCCESS;
            }
        }  
        return INPUT;
    }
    

    public static Map<String, Object> getUserSession() {
        return userSession;
    }

    public static void setUserSession(Map<String, Object> userSession) {
        AddPlan.userSession = userSession;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public boolean isValid(String a) {
        return a != null && a.length() >= 0;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
