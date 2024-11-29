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


@WebServlet("/StCheckServlet")
public class StCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("StCheckServlet");
        String ST= req.getParameter("ST");
        if (SystemCache.getStCache().containsKey(ST)){
            User user= SystemCache.getStCache().remove(ST);  //验证成功后删除ST
            //设置响应数据

            resp.getWriter().write(user.getUsername()+"\n");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String loginDate = simpleDateFormat.format(user.getLoginDate());
            resp.getWriter().write(loginDate);
        }else{
            resp.getWriter().write("INVALID_ST");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
