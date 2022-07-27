package com.proj.example.action;

import com.proj.example.model.User;
import com.proj.example.utility.Admin;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class Login extends ActionSupport implements SessionAware{

    private User user;
    private String error = "";
    private String username;
    private String password;
    private String usernameError;
    private String passwordError;
    private Map<String, Object> userSession = ActionContext.getContext().getSession();

    

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

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

    public String execute() throws Exception {
        if (isValid(getUsername()) && isValid(getPassword())) {
            if (Admin.Login(this)) {
                
                return SUCCESS;
            } else {
                error = "Login failed!";
                return ERROR;
            }
        } else {
            return INPUT;
        }
    }

    public boolean isValid(String a) {

        return a != null && a.length() >= 0;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        userSession = session;
        
    }

    public Map<String, Object> getUserSession() {
        return userSession;
    }

    public void setUserSession(Map<String, Object> userSession) {
        this.userSession = userSession;
    }

    public String logout() throws Exception {
        if (Admin.logout() == 1) {
            return SUCCESS;
        }
        return ERROR;
    }

}