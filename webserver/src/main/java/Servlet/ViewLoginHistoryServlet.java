package Servlet;

import cache.SystemCache;
import entity.LoginHistory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static dao.LoginHistoryManager.viewLoginHistory;

@WebServlet("/ViewLoginHistoryServlet")
public class ViewLoginHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("查看登录历史");
//        ArrayList<LoginHistory> loginHistories = viewLoginHistory();
//        req.setAttribute("loginHistories", loginHistories);
        req.getRequestDispatcher("/viewLoginHistory.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
