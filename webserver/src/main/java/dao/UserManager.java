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
    public static int isDisabled(String username) {
        Connection conn = null;
        Statement stmt = null;
        int isDisabled = 0;
        ResultSet rs;
        try {
            conn = DBConnect.getConn();
            String sql = "select isDisabled from user where username = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                isDisabled = rs.getInt("isDisabled");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDisabled;
    }

    public static User JudgeUser(String username, String password) {
        Connection conn = null;
        Statement stmt = null;
        User user = null;
        ResultSet rs;
        try {
            conn = DBConnect.getConn();
            String sql = "select username , password, isDisabled from user where username = ? and password = ?";
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
    public static void disableUser(String username) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DBConnect.getConn();
            String sql = "update user set isDisabled = 1 where username = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            int updated=preparedStatement.executeUpdate();
            if (updated>0){
                System.out.println("用户"+username+"已被禁用");
            }else{
                System.out.println("用户"+username+"禁用失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConn(conn, stmt);
        }
    }
}
