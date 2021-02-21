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
        String size = request.getParameter("size");
        BossBean bossBean = (BossBean) request.getSession().getAttribute("name");

        SumBean sumBean = new SumBean();
        sumBean.setTotalBlogs(blogServiceImp.totalBlogs(bossBean.getName()));
        sumBean.setAttention(blogServiceImp.attentions(bossBean.getName()));
        sumBean.setFans(blogServiceImp.Fans(bossBean.getName()));
        request.getSession().setAttribute("sum", sumBean);

        if(size.equals("all")){
            List<BlogBean> blogs = blogServiceImp.findBlogs();
            request.getSession().setAttribute("AllBlogs",blogs);
            request.getRequestDispatcher("/View/Blog.jsp").forward(request,response);
        }else{
            HttpSession session = request.getSession();
            List<BlogBean> blogsByBoosId = blogServiceImp.findBlogsByBoosId(bossBean.getName());
            request.getSession().setAttribute("blogs",blogsByBoosId);
            request.getRequestDispatcher("/View/Information.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
    }
}
