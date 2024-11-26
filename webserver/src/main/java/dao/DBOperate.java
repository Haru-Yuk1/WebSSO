package dao;

import entity.User;

import java.sql.*;
import java.util.Date;

public class DBOperate {
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
}
