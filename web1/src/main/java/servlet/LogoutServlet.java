package servlet;

import cache.SystemCache;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("正在登出web1");
        //删除session

        HttpSession session = req.getSession(false);
        System.out.println(session);
        if (session != null) {
            System.out.println("正在删除web1登录状态");
            session.invalidate();
        }

        //删除缓存
        SystemCache.getRegisteredUsers().remove(SystemCache.getCurrentUser());
        SystemCache.setCurrentUser(null);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
