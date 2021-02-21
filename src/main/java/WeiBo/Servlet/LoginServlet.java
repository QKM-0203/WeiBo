package WeiBo.Servlet;

import WeiBo.Service.Imp.bossServiceImp;
import WeiBo.Bean.BossBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * 登录获取请求参数，然后从数据库验证密码是否正确，利用druid和jdbcTemplate来判断
 * 密码正确登录成功
 * 错误登录失败
 *是一些简单的主要是将最近学得连接起来
 */
@WebServlet(value = "/LoginServlet",name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //首先判断验证码是否正确
        HttpSession session = request.getSession();
        session.removeAttribute("ku");
        String checkBoard = request.getParameter("checkBoard");
        if(checkBoard.equals("")){
            session.setAttribute("ku","验证码为空");
            response.sendRedirect(request.getContextPath()+"/View/Login.jsp");
        } else if(session.getAttribute("checkBoard").equals(checkBoard)){
            session.removeAttribute("checkBoard");
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("Name")){
                    request.getRequestDispatcher("/findBlogServlet?size=all").forward(request,response);
                }
            }
            BossBean bossBean = new BossBean();
            String user = request.getParameter("boss");
            String password = request.getParameter("password");
            if(user == null | password == null){
                session.setAttribute("ku","用户名或密码为空");
                response.sendRedirect(request.getContextPath()+"/View/Login.jsp");
            }
            bossBean.setName(user);
            bossBean.setPassword(password);
            bossServiceImp bossServiceImp = new bossServiceImp();
            BossBean bossBean1 = bossServiceImp.findBoss(bossBean);
            if(bossBean1 != null){
                session.setAttribute("name",bossBean1);
                String remember = request.getParameter("remember");
                if(remember != null){
                    Cookie cookie = new Cookie("Name", bossBean.getName());
                    Cookie password1 = new Cookie("password", bossBean.getPassword());
                    cookie.setMaxAge(60);
                    password1.setMaxAge(60);
                    response.addCookie(cookie);
                    response.addCookie(password1);
                }
               response.sendRedirect(request.getContextPath()+"/findBlogServlet?size=all");
            } else{
                session.setAttribute("ku","用户名或密码错误");
                response.sendRedirect(request.getContextPath()+"/View/Login.jsp");

            }
        }else{
            session.removeAttribute("checkBoard");
            session.setAttribute("ku","验证码错误");
            response.sendRedirect(request.getContextPath()+"/View/Login.jsp");

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doPost(request,response);
    }
}
