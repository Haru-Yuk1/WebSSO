package Servlet;

import cache.SystemCache;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@WebServlet("/JumpServlet")
public class JumpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");
        System.out.println("action:"+action);

        if(action.equals("web1")){
            System.out.println("正在跳转到web1");
            //使用重定向，将用户信息传递给web1
            resp.sendRedirect("/web1");
        }
        else if(action.equals("web2")) {
            System.out.println("正在跳转到web2");

            //使用重定向，将用户信息传递给web2
            resp.sendRedirect("/web2");
        }
        else if (action.equals("logout")){
            System.out.println("正在登出");
            resp.sendRedirect("http://localhost:8080/webserver/LogoutServlet");
        }
        else if (action.equals("disable")){
            System.out.println("正在禁用用户");

            resp.sendRedirect("http://localhost:8080/webserver/DisableUserServlet");
        } else if (action.equals("viewLoginHistory")){
            System.out.println("查看登录历史");
            resp.sendRedirect("http://localhost:8080/webserver/viewLoginHistory.jsp");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
