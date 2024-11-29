package dao;

import entity.User;

import java.sql.*;
import java.util.Date;

public class UserManager {
    public static Boolean isUserExist(String username) {
        Connection conn = null;
        Statement stmt = null;
        Boolean isExist = false;
        ResultSet rs;
        try {
            conn = DBConnect.getConn();
            String sql = "select username from user where username = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                isExist = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConn(conn, stmt);
        }
        return isExist;
    }
    public static User JudgeUser(String username, String password) {
        Connection conn = null;
        Statement stmt = null;
        User user = null;
        ResultSet rs;
        try {
            conn = DBConnect.getConn();
            String sql = "select username , password from user where username = ? and password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);//将输入字符串同时在学号和姓名中查询
            preparedStatement.setString(2, password);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String selUsername = rs.getString("username");
                String selPassword = rs.getString("password");
                user = new User(selUsername,selPassword,new Date());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConn(conn, stmt);
        }
        return user;
    }
    public static User registerUser(String username, String password) {
        Connection conn = null;
        Statement stmt = null;
        User user = null;
        ResultSet rs;
        try {
            conn = DBConnect.getConn();
            String sql = "insert into user(username,password) values(?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            user = new User(username,password,new Date());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConn(conn, stmt);
        }
        return user;
    }
}
