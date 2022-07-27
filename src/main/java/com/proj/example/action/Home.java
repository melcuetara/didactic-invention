package com.proj.example.action;

import java.nio.file.attribute.UserPrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.proj.example.model.Plan;
import com.proj.example.utility.Admin;

import javassist.bytecode.stackmap.BasicBlock.Catch;

public class Home extends ActionSupport implements SessionAware {

    @Override
    public void setSession(Map<String, Object> session) {
        userSession = session;  
    }
    
    private static Map<String, Object> userSession = ActionContext.getContext().getSession();
    private ArrayList<Plan> plans = new ArrayList<>();
    
    public String execute() throws Exception {

        Connection conn = Admin.conn();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM plan WHERE user_id = ?");
        ResultSet rs = null;
        try {
            ps.setInt(1, Integer.parseInt("" + userSession.get("id")) );
            rs = ps.executeQuery();
            while (rs.next()) {
                Plan plan = new Plan();
                plan.setId(rs.getInt(2));
                plan.setPlan_id(rs.getInt(1));
                plan.setName(rs.getString(3));
                plan.setMax_calories(rs.getDouble(4));
                plan.setDate(rs.getDate(5));
                plan.setDescription(rs.getString(6));
                plans.add(plan);
                System.out.println("Plan id" + plans.get(0).getPlan_id());
            }
            populateCurrentCal();
        } catch (Exception e ) {
            e.printStackTrace();
        } finally {
            Admin.closeObj(conn, ps, rs);
        }
        return SUCCESS;
    }

    public void populateCurrentCal() throws Exception {

        Connection conn = Admin.conn();
        PreparedStatement ps = conn.prepareStatement("SELECT calories FROM meal WHERE plan_id = ?");
        ResultSet rs = null;
        try {
            for (int i = 0; i < plans.size(); i++) {
                ps.setInt(1, plans.get(i).getPlan_id());
                rs = ps.executeQuery();
                double cal = 0;
                while (rs.next()) {
                    cal += rs.getDouble(1);
                }
                System.out.println(cal);
                plans.get(i).setCurrent_calories(cal);
            }
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> getUserSession() {
        return userSession;
    }
    public static void setUserSession(Map<String, Object> userSession) {
        Home.userSession = userSession;
    }

    public ArrayList<Plan> getPlans() {
        return plans;
    }

    public void setPlans(ArrayList<Plan> plans) {
        this.plans = plans;
    }

    
    
}
