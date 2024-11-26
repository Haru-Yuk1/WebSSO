package filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(urlPatterns = { "/index.jsp"})
public class LoginFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("LoginFilter");
        System.out.println(req.getSession().getId());
        if (req.getSession().getAttribute("user")==null){

            res.sendRedirect("/webserver/login.jsp");
        }
    }
}
