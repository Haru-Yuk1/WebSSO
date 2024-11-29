package servlet;

import cache.SystemCache;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("正在登出web2");
        //删除session
        HttpSession session = req.getSession(false);
        if (session != null) {
            System.out.println("正在删除web2登录状态");
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
