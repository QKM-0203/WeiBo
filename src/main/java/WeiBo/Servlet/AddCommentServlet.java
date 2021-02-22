package WeiBo.Servlet;

import WeiBo.Bean.BlogBean;
import WeiBo.Bean.BossBean;
import WeiBo.Bean.CommentBean;
import WeiBo.Service.Imp.CommentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(value = "/addCommentServlet",name = "addCommentServlet")
public class AddCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date date = new Date();
        String comment = request.getParameter("comment");
        String blogCreatAtAndName = request.getParameter("blogCreatAtAndName");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(date);
        BossBean bossBean = (BossBean) request.getSession().getAttribute("name");
        CommentBean commentBean = new CommentBean();
        commentBean.setCreatAt(dateNowStr);
        commentBean.setBossId(bossBean.getId());
        commentBean.setComment(comment);
        commentBean.setBlogCreatAtAndName(blogCreatAtAndName);
        CommentServiceImpl commentService = new CommentServiceImpl();
        commentService.addComment(commentBean);
        String id = request.getParameter("id");
        if(id.equals("all")) {
            List<BlogBean> allBlogs = (List<BlogBean>) request.getSession().getAttribute("AllBlogs");
            addComment(allBlogs,blogCreatAtAndName,commentBean);
            String bossId = blogCreatAtAndName.substring(19);
            request.getSession().setAttribute("AllBlogs", allBlogs);
            response.sendRedirect(request.getContextPath() + "/View/Blog.jsp");
        }else{
            List<BlogBean> blogs = (List<BlogBean>) request.getSession().getAttribute("blogs");
            addComment(blogs,blogCreatAtAndName,commentBean);
            request.getSession().setAttribute("blogs", blogs);
            response.sendRedirect(request.getContextPath() + "/View/Information.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
    }
    public void addComment(List<BlogBean> blogs,String blogCreatAtAndName,CommentBean commentBean){
        for (BlogBean blog : blogs) {
            if (blog.getCreatAtAndName().equals(blogCreatAtAndName)) {
                List<CommentBean> listCom = blog.getListCom();
                listCom.add(0, commentBean);
                break;
            }
        }
    }
}
