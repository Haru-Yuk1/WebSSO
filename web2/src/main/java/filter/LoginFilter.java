package filter;

import cache.SystemCache;
import entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebFilter(urlPatterns = { "/index.jsp"})
public class LoginFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("Web2LoginFilter");

        //检查当前请求有没有会话，如果没有会话，则返回null，而不是创建一个新会话
        HttpSession session=req.getSession(false);
        if (session!=null&& session.getAttribute("username")!=null){
            chain.doFilter(req, res);//用户已登录，放行
            return;
        }
        //用户未登录
        //获取ST
        String st = req.getParameter("ST");
        if (st!=null){
            //检查ST是否有效
            String validationUrl="http://localhost:8080/webserver/StCheckServlet?ST="+st;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader
                    (new URL(validationUrl).openStream()))) {
                String username = reader.readLine();
                String loginDate = reader.readLine();
                System.out.println("username="+username);
                System.out.println("loginDate="+loginDate);
                if (username!=null){
                    session=req.getSession();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = sdf.parse(loginDate);
                    //用户登录成功，将用户信息存入缓存
                    User user=new User(username,date);
                    SystemCache.getRegisteredUsers().add(user);
                    session.setAttribute("username",username);
//                    session.setAttribute("loginDate",loginDate);
                    chain.doFilter(req, res);
                    return;
                }
                else{
                    //ST无效
                    System.out.println("ST无效");
                    res.sendRedirect("/webserver/login.jsp?backUrl="+req.getRequestURL());
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();

            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        //没有令牌重定向到登录页面
        res.sendRedirect("/webserver/login.jsp?backUrl="+req.getRequestURL());
    }
}
