package Servlet;

import cache.SystemCache;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/JumpServlet")
public class JumpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");
        System.out.println("action:"+action);

        User currentUser = SystemCache.getCurrentUser();
        if(action.equals("web1")){
            System.out.println("正在跳转到web1");
            //不能请求转发，因为web1是另一个web服务器

            //使用重定向，将用户信息传递给web1
            resp.sendRedirect("/web1?username="+currentUser.getUsername()+"&loginDate="+currentUser.getLoginDate());


        }
        else if(action.equals("web2")) {
            System.out.println("正在跳转到web2");
            //不能使用请求转发
//            req.setAttribute("user",currentUser);
//            req.getRequestDispatcher("/web2").forward(req, resp);

//            resp.sendRedirect("/web2");
            //使用重定向，将用户信息传递给web2
            resp.sendRedirect("/web2?username="+currentUser.getUsername()+"&loginDate="+currentUser.getLoginDate());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
