package entity;

import java.util.Date;

public class LoginHistory {
    private String username;
    private Date loginDate;

    public LoginHistory(String username, Date loginDate) {
        this.username = username;
        this.loginDate = loginDate;
    }

    public LoginHistory() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
}
