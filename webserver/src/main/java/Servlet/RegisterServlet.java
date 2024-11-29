package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static dao.UserManager.isUserExist;
import static dao.UserManager.registerUser;
import entity.User;
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("username") != null && req.getParameter("password") != null ) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String confirmPassword = req.getParameter("confirmPassword");
            if (!password.equals(confirmPassword)) {
                String wrongInfo = "两次密码不一致";
                req.setAttribute("wrongInfo", wrongInfo);
                req.getRequestDispatcher("register.jsp").forward(req, resp);
                return;
            }
            if (isUserExist(username)) {
                String wrongInfo = "该用户已存在";
                req.setAttribute("wrongInfo", wrongInfo);
                req.getRequestDispatcher("register.jsp").forward(req, resp);
                return;
            }
            User user =registerUser(username, password);
            if (user==null){
                //设置输入错误弹窗内容并记录url
                req.setAttribute("wrongInfo","注册失败，请重新注册！！！");
                //请求转发到登录页
                req.getRequestDispatcher("/register.jsp").forward(req,resp);
                return;
            }

            System.out.println("username: " + username + " password: " + password);
            resp.sendRedirect("http://localhost:8080/webserver/login.jsp");
        } else {
            resp.sendRedirect("http://localhost:8080/webserver/register.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
