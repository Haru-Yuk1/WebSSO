package Servlet;

import cache.SystemCache;
import dao.DBOperate;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

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
            //使用CAS方案

            //创建Session并记录登录状态
            HttpSession session = req.getSession();
            session.setAttribute("username",user.getUsername());
            session.setAttribute("loginDate",user.getLoginDate());
            System.out.println(req.getSession().getId());

            //写下SSO域下的Cookie


            //生成随机Token，处理生成32位随机连续字符串
            String Token = UUID.randomUUID().toString().replace("-", "");
            //生成cookie
            Cookie cookie = new Cookie("CAS-TGC",Token);
            cookie.setPath("/");
            resp.addCookie(cookie);

            //生成ST
            String st=UUID.randomUUID().toString().replace("-","");

            //将用户信息加入缓存
            SystemCache.setCurrentUser(user);
            SystemCache.getRegisteredUsers().add(user);

            //导航去主页
            resp.sendRedirect("index.jsp");



//            //创建cookie,用于
//            Cookie cookie=new Cookie("username",username);
//
//            //设置cookie的有效时间
//            cookie.setMaxAge(60*60*24);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
