package filter;

import cache.SystemCache;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter(urlPatterns = {"/index.jsp"})
public class LoginFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("SSOLoginFilter");
        //检查当前请求有没有会话，如果没有会话，则返回null，而不是创建一个新会话
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            chain.doFilter(req, res);//用户已登录，放行
            return;
        }
        //如果当前没有会话

        //检查TGT
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("TGT") && SystemCache.getTgtCache().containsKey(cookie.getValue())) {
                    String TGT = cookie.getValue();
                    chain.doFilter(req, res);
                    break;
                }
            }
        }
        res.sendRedirect("/webserver/login.jsp");


    }
}
