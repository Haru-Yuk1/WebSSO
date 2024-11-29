package Servlet;

import cache.SystemCache;
import dao.UserManager;
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
        //获取输入信息

        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String backUrl=req.getParameter("backUrl");

        //检查是否已有TGT
        String TGT=null;
        Cookie[] cookies=req.getCookies();
        if (cookies!=null){
            for (Cookie cookie:cookies){
                if (cookie.getName().equals("TGT")){
                    TGT=cookie.getValue();      //获取cookie
                    cookie.setMaxAge(0);        //删除cookie
                    resp.addCookie(cookie); //将删除后的cookie添加到response
                }
            }
        }
        System.out.println("TGT:"+TGT);
        if (TGT!=null && SystemCache.getTgtCache().containsKey(TGT)){
            //如果有TGT且TGT有效
            User user=SystemCache.getTgtCache().get(TGT);
            //直接签发ST
            if (backUrl!=null&&!backUrl.trim().isEmpty()&& !backUrl.equals("null")){
                //生成ST并缓存
                String st=UUID.randomUUID().toString().replace("-","");
                SystemCache.getStCache().put(st,user);
                //带着ST跳转回去
                resp.sendRedirect(backUrl+"?ST="+st);
            } else{
                //未提供backUrl，跳转到默认主页
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
            }
            return;
        }

        //判断是否是数据库中的合法用户
        User user = UserManager.JudgeUser(username,password);
        if (user==null){
            //设置输入错误弹窗内容并记录url
            req.setAttribute("wrongInfo","账号或密码输入错误，请重新输入！！！");
//            req.setAttribute("backUrl",backUrl);
            //请求转发到登录页
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
        else{
            //使用CAS方案

            //创建Session并记录登录状态
            HttpSession session = req.getSession();
            session.setAttribute("username",user.getUsername());
//            session.setAttribute("loginDate",user.getLoginDate());
//            System.out.println(req.getSession().getId());


            if (TGT!=null){
                //生成ST并缓存
                String st=UUID.randomUUID().toString().replace("-","");
                SystemCache.getStCache().put(st,user);
                //带着ST跳转回去
                resp.sendRedirect(backUrl+"?ST="+st);
                return;
            }
            //生成随机TGT
            String tgt = UUID.randomUUID().toString().replace("-", "");
            //将TGT写入cookie
            Cookie cookie = new Cookie("TGT",tgt);
            cookie.setPath("/");        //设置为根目录，确保所有web都能访问
            cookie.setHttpOnly(true);   //防止通过js获取cookie
            resp.addCookie(cookie);

            //将TGT加入缓存
            SystemCache.getTgtCache().put(tgt,user);
            //将用户信息加入缓存
            SystemCache.setCurrentUser(user);
            SystemCache.getRegisteredUsers().add(user);

            //如果是从web1或web2跳转过来的
            if (backUrl!=null&&!backUrl.trim().isEmpty()&& !backUrl.equals("null")){
                System.out.println("正在跳转");
                //生成ST并缓存
                String st=UUID.randomUUID().toString().replace("-","");
                SystemCache.getStCache().put(st,user);
                //带着ST跳转回去
                resp.sendRedirect(backUrl+"?ST="+st);
            } else{
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
