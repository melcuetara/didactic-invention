package com.proj.example.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.proj.example.utility.Admin;

public class Register extends ActionSupport implements SessionAware {
    
    private String username;
    private String password;
    private String error;
    private static Map<String, Object> userSession = ActionContext.getContext().getSession();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValid(String a) {
        return a != null && a.length() >= 6;
    }

    public String execute() throws Exception {

        if (isValid(getUsername()) || isValid(getPassword())) { /* check if input is not empty */
            int val = Admin.Register(this);
            if (val == 1) {
                return SUCCESS;
            } else if (val == 2) {
                error = "Entered username already existed, please input other username.";
            }
            return ERROR;
        }
        return INPUT;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        userSession = session;
        
    }

    public static Map<String, Object> getUserSession() {
        return userSession;
    }

    public static void setUserSession(Map<String, Object> userSession) {
        Register.userSession = userSession;
    }

}
