package Servlet;

import cache.SystemCache;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static dao.UserManager.disableUser;

@WebServlet("/DisableUserServlet")
public class DisableUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = SystemCache.getCurrentUser();
        disableUser(user.getUsername());
        System.out.println("用户已禁用");
        resp.sendRedirect("http://localhost:8080/webserver/LogoutServlet");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
