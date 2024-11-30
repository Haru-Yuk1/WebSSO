package Servlet;

import cache.SystemCache;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取TGT并删除
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("TGT")) {
                    //删除TGT
                    String TGT = cookie.getValue();
                    SystemCache.getTgtCache().remove(TGT);
                    cookie.setMaxAge(0);// 删除cookie
                    cookie.setPath("/"); // 确保路径正确
                    resp.addCookie(cookie); // 将删除后的cookie添加到响应中，必须添加，才能删除
                    break;
                }
            }
        }

        HttpSession session = req.getSession(false);
        //删除缓存
        for (User user:SystemCache.getRegisteredUsers()){
            if (user.getUsername().equals(session.getAttribute("username"))){
                SystemCache.getRegisteredUsers().remove(user);
                break;
            }
        }
        //删除session
        if (session != null) {
            session.removeAttribute("username");
            session.invalidate();

        }


        //通知web1 和 web2 删除登录缓存
//        notifyLogout("http://localhost:8080/web1/LogoutServlet");
//        notifyLogout("http://localhost:8080/web2/LogoutServlet");


        resp.sendRedirect("http://localhost:8080/webserver/logout.jsp");
    }

    private void notifyLogout(String logoutUrl) {
        try {
            URL url = new URL(logoutUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                System.out.println("Logout notification to " + logoutUrl + " succeeded.");
            } else {
                System.err.println("Logout notification to " + logoutUrl + " failed with response code: " + code);
            }
            connection.disconnect();
        } catch (IOException e) {
            System.err.println("Error notifying logout URL: " + logoutUrl);
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
