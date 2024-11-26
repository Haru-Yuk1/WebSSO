package Servlet;

import cache.SystemCache;
import dao.DBOperate;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username,password;
        username=req.getParameter("username");
        password=req.getParameter("password");

        //判断是否是数据库中的合法用户
        User user = DBOperate.JudgeUser(username,password);
        if (user==null){
            //设置输入错误弹窗内容并记录url
            req.setAttribute("wrongInfo","账号或密码输入错误，请重新输入！！！");
//            req.setAttribute("backUrl",backUrl);
            //请求转发到登录页
            req.getRequestDispatcher("/login.jsp").forward(req,resp);

            //导航回登录页，并提示错误
//            resp.sendRedirect("login.jsp");
        }
        else{
            //导航去主页
            resp.sendRedirect("index.jsp");


            //将用户信息加入缓存
            SystemCache.setCurrentUser(user);
            SystemCache.getRegisteredUsers().add(user);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
