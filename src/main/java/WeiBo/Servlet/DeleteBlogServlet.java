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

@WebServlet(value = "/deleteBlogServlet",name = "deleteBlogServlet")
public class DeleteBlogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String time = request.getParameter("time");
        BossBean bossBean= (BossBean)session.getAttribute("name");
        BlogServiceImp blogServiceImp = new BlogServiceImp();
        blogServiceImp.deleteBlog(bossBean.getName(),time);
        SumBean sum = (SumBean) request.getSession().getAttribute("sum");
        sum.setTotalBlogs(sum.getTotalBlogs()-1);
        request.getSession().setAttribute("sum",sum);
        String id = request.getParameter("id");
        if(id.equals("all")) {
            List<BlogBean> blogs = (List<BlogBean>) request.getSession().getAttribute("AllBlogs");
             deleteListElement(blogs, bossBean.getName(),time);
            this.getServletContext().setAttribute("AllBlogs", blogs);
            response.sendRedirect(request.getContextPath() + "/View/Blog.jsp");
        }else{
            List<BlogBean> blogs = (List<BlogBean>)request.getSession().getAttribute("blogs");
            deleteListElement(blogs, bossBean.getName(),time);
            request.getSession().setAttribute("blogs", blogs);
            response.sendRedirect(request.getContextPath() + "/View/Information.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doPost(request, response);
    }

    public void deleteListElement(List<BlogBean> blogs,String bossId,String time){
        for (int i = 0; i < blogs.size(); i++) {
            if (blogs.get(i).getBossId().equals(bossId) && blogs.get(i).getCreatAtAndName().equals(time)) {
                blogs.remove(i);
                i--;
                break;
            }
        }

    }
}
