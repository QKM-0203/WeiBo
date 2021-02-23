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
        String checkBoard = request.getParameter("checkBoard");
        if(checkBoard.equals("")){
            request.setAttribute("ku","验证码为空");
            response.sendRedirect(request.getContextPath()+"/View/Login.jsp");
        } else if(session.getAttribute("checkBoard").equals(checkBoard)){
            session.removeAttribute("checkBoard");
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("Id")){
                    request.getRequestDispatcher("/findBlogServlet?bossId=1").forward(request,response);
                }
            }
            BossBean bossBean = new BossBean();
            String Id = request.getParameter("Id");
            String password = request.getParameter("password");
            if(Id == null | password == null){
                request.setAttribute("ku","用户名或密码为空");
                request.getRequestDispatcher("/View/Login.jsp").forward(request,response);
            }

            bossBean.setId(Id);
            bossBean.setPassword(password);
            bossServiceImp bossServiceImp = new bossServiceImp();
            BossBean bossBean1 = bossServiceImp.findBoss(bossBean);
            if(bossBean1 != null){
                BossBean bossBean2 = (BossBean) request.getSession().getAttribute("name");
                if(bossBean2.getId().equals(bossBean1.getId())){
                    request.setAttribute("ku","不能重复登录");
                    request.getRequestDispatcher("/View/Login.jsp").forward(request,response);
                }
                session.setAttribute("name",bossBean1);
                String remember = request.getParameter("remember");
                if(remember != null){
                    Cookie cookie = new Cookie("Id", bossBean.getId());
                    Cookie password1 = new Cookie("password", bossBean.getPassword());
                    cookie.setMaxAge(60);
                    password1.setMaxAge(60);
                    response.addCookie(cookie);
                    response.addCookie(password1);
                }
               response.sendRedirect(request.getContextPath()+"/findBlogServlet?bossId=1");
            } else{
                request.setAttribute("ku","用户名或密码错误");
                request.getRequestDispatcher("/View/Login.jsp").forward(request,response);
                response.sendRedirect(request.getContextPath()+"/View/Login.jsp");

            }
        }else{
            session.removeAttribute("checkBoard");
            request.setAttribute("ku","验证码错误");
            request.getRequestDispatcher("/View/Login.jsp").forward(request,response);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doPost(request,response);
    }
}
