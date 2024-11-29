package dao;

import entity.LoginHistory;

import java.sql.*;

import java.util.ArrayList;
import java.util.Date;

public class LoginHistoryManager {
    public static void recordLogin(String username, Date logindate) {
        Connection conn = DBConnect.getConn();
        String query = "INSERT INTO login_history (username, logindate) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setTimestamp(2,  new Timestamp(logindate.getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<LoginHistory> viewLoginHistory(String username) {
        Connection conn = DBConnect.getConn();
        String query = "SELECT * FROM login_history where username = ?";
        ArrayList<LoginHistory> loginHistories = new ArrayList<>();;
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LoginHistory loginHistory = new LoginHistory();
                loginHistory.setUsername(rs.getString("username"));
                loginHistory.setLoginDate(new Date(rs.getTimestamp("logindate").getTime()));
                loginHistories.add(loginHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loginHistories;
    }
}
