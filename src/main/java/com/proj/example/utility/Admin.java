package com.proj.example.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.interfaces.RSAPrivateKey;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Map;
import java.util.Random;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.proj.example.action.AddPlan;
import com.proj.example.action.Login;
import com.proj.example.action.Register;

public class Admin implements SessionAware {
    @Override
    public void setSession(Map<String, Object> session) {
        userSession = session;
    }

    private static final String SALT_START = "297v5yOA*&Wvn21";
    private static final String SALT_END = "|SDF.jc*(Sfe";

    private static String token;

    private static Map<String, Object> userSession = ActionContext.getContext().getSession();

    public static Map<String, Object> getUserSession() {
        return userSession;
    }

    public static void setUserSession(Map<String, Object> userSession) {
        Admin.userSession = userSession;
    }

    public static Connection conn() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mealplannerdb?useTimezone=true&serverTimezone=UTC", "root", "meljamaica");
    }

    public static boolean Login(Login in) throws Exception {

        Connection con = conn();
        PreparedStatement ps = con.prepareStatement("SELECT user_id, username, password FROM user WHERE username = ?");
        ResultSet rs = null;

        try {
            ps.setString(1, in.getUsername());
            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString(2).equals(in.getUsername()) && /* Check if credentials match */
                        rs.getString(3).equals(getHashCode(in.getPassword()))) {
                    if (writeToken(rs.getString(2))) {
                        userSession.put("token", getToken());
                        userSession.put("username", rs.getString(2));
                        userSession.put("id", rs.getString(1));
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeObj(con, ps, rs);
        }
        return false;
    }

    public static int Register(Register reg) throws Exception {

        int row = 0;
        Connection con = conn();
        PreparedStatement ps = con.prepareStatement("INSERT INTO user(username, password) VALUES(? , ?)");

        try {
            ps.setString(1, reg.getUsername());
            ps.setString(2, getHashCode(reg.getPassword()));
            row = ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            row = 2; /* return 2 if duplicate username */
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeObj(con, ps, null);
        }
        return row;
    }

    public static String getHashCode(String pw) {

        StringBuilder s = new StringBuilder();
        String format = getSALT_START() + pw + getSALT_END();

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(format.getBytes(StandardCharsets.UTF_8));
            for (int i = 0; i < hash.length; i++) {
                s.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s.toString();
    }

    public static int savePlan(AddPlan ap) throws Exception {
        int row = 0;
        Connection con = conn();
        ResultSet rs = null;
        PreparedStatement ps = con.prepareStatement("INSERT INTO plan(user_id, name, max_calories, date, description) VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        try {
            // ps.setInt(1, Integer.parseInt("" + userSession.get("id")));
            // ps.setString(2, (String) ap.getPlanName());
            // ps.setDouble(3, ap.getMaxCalories());
            // ps.setString(4, ap.getPlanDate());
            int i = Integer.parseInt("" + userSession.get("id"));
            ps.setInt(1, i);
            ps.setString(2, ap.getPlanName());
            ps.setDouble(3, ap.getMaxCalories());
            ps.setString(4, ap.getPlanDate());
            ps.setString(5, ap.getDescription());
            row = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                System.out.println(row);
                System.out.println("Here" + rs.getString(1));
                userSession.put("planId", rs.getString(1));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeObj(con, ps, rs);
        }
        return row;
    }



    public static String generateToken() {

        Random random = new Random();
        String abc = "abcdefghijklmnopqrstuvwxyz!@#$%^&*()";
        String str = "";

        for (int i = 0; i < 20; i++) {
            str += abc.charAt(random.nextInt(abc.length()));
        }
        return str;
    }

    public static boolean writeToken(String user) throws Exception {

        boolean write;
        Connection con = conn();
        PreparedStatement ps = con.prepareStatement("UPDATE user SET token = ? WHERE username = ?");
        setToken(generateToken());

        try {
            ps.setString(1, getToken());
            ps.setString(2, user);
            ps.executeUpdate();
            write = true;
        } catch (Exception e) {
            write = false;
            e.printStackTrace();
        } finally {
            closeObj(con, ps, null);
        }
        return write;
    }

    public static void main(String[] args) throws Exception {

    }

    public static boolean isSessionValid() throws Exception {

        boolean found = false;
        Connection con = conn();
        PreparedStatement ps = con.prepareStatement("SELECT user_id, token FROM user WHERE user_id = ? AND token = ?");
        
        try {
            if (!getUserSession().isEmpty()) {
                ps.setString(1, (String) userSession.get("id"));
                ps.setString(2, (String) userSession.get("token"));
                if (ps.executeQuery() != null) {
                    found = true;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            closeObj(con, ps, null);
        }
        return found;
    }

    public static int logout() throws Exception {

        int i = 0;
        Connection con = conn();
        PreparedStatement ps = con.prepareStatement("UPDATE user SET token = NULL WHERE username = ?");

        try {
            ps.setString(1, (String) userSession.get("username"));
            i = ps.executeUpdate();
            userSession.clear();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeObj(con, ps, null);
        }
        return i;
    }

    public static int addMeal() throws Exception {
        
        int i = 0;
        Connection con = conn();
        PreparedStatement ps = con.prepareStatement("INSERT into ");
        return i;
    }

    public static void closeObj(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Admin.token = token;
    }

    public static String getSALT_START() {
        return SALT_START;
    }

    public static String getSALT_END() {
        return SALT_END;
    }
}
