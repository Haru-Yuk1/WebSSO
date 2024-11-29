package servlet;

import cache.SystemCache;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet("/JumpServlet")
public class JumpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");
        System.out.println("action:"+action);

        User currentUser = SystemCache.getCurrentUser();
        if(action.equals("webserver")){
            System.out.println("正在跳转到webServer");
            //不能请求转发，因为web1是另一个web服务器
            //使用重定向，将用户信息传递给web1
            resp.sendRedirect("/webserver");
        }
        else if(action.equals("web1")) {
            System.out.println("正在跳转到web1");

            //使用重定向，将用户信息传递给web2
            resp.sendRedirect("/web1");
        }
        else if (action.equals("logout")){
            System.out.println("正在登出");
            resp.sendRedirect("http://localhost:8080/webserver/LogoutServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
