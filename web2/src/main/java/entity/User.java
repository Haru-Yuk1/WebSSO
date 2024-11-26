package entity;


import java.util.Date;

public class User {
    private String username;
    private String password;
    private Date loginDate;

    public User(String username, String password, Date loginDate) {
        this.username = username;
        this.password = password;
        this.loginDate = loginDate;
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

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
}
