package WeiBo.Servlet;

import WeiBo.Bean.BlogBean;
import WeiBo.Bean.BossBean;
import WeiBo.Bean.SumBean;
import WeiBo.Service.Imp.BlogServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/findBlogServlet",name = "findBlogServlet")
public class FindBlogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BlogServiceImp blogServiceImp = new BlogServiceImp();
        String bossId = request.getParameter("bossId");
        BossBean bossBean = (BossBean) request.getSession().getAttribute("name");
        SumBean sumBean = new SumBean();
        if(bossId.equals("1")){
            List<BlogBean> blogs = blogServiceImp.findBlogs();
            request.getSession().setAttribute("AllBlogs",blogs);
             sumBean.setTotalBlogs(blogServiceImp.totalBlogs(bossBean.getId()));
            sumBean.setAttention(blogServiceImp.attentions(bossBean.getId()));
            sumBean.setFans(blogServiceImp.Fans(bossBean.getId()));
            request.getSession().setAttribute("sum", sumBean);
            request.getRequestDispatcher("/View/Blog.jsp").forward(request,response);


        }else{
            sumBean.setTotalBlogs(blogServiceImp.totalBlogs(bossId));
            sumBean.setAttention(blogServiceImp.attentions(bossId));
            sumBean.setFans(blogServiceImp.Fans(bossId));
            request.getSession().setAttribute("sum", sumBean);

            List<BlogBean> blogsByBoosId = blogServiceImp.findBlogsByBoosId(bossId);
            request.getSession().setAttribute("blogs",blogsByBoosId);
            if(bossId.equals(bossBean.getId())){
                request.getRequestDispatcher("/View/Information.jsp").forward(request,response);
            }else{
                request.getRequestDispatcher("/View/OtherInformation.jsp").forward(request,response);

            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
    }
}
