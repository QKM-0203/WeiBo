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

@WebServlet(value = "/deleteCommentServlet",name = "deleteCommentServlet")
public class DeleteCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String creatAt = request.getParameter("creatAt");
        BossBean bossBean = (BossBean) request.getSession().getAttribute("name");
        String blogCreatAtAndName = request.getParameter("blogCreatAtAndName");
        CommentServiceImpl commentService = new CommentServiceImpl();
        commentService.deleteComment(bossBean.getName(),creatAt);
        String id = request.getParameter("id");
        if(id.equals("all")) {
            List<BlogBean> blogs = (List<BlogBean>) request.getSession().getAttribute("AllBlogs");
            deleteListElement(blogs,blogCreatAtAndName,bossBean.getName(),creatAt);
            request.getSession().setAttribute("AllBlogs", blogs);
            response.sendRedirect(request.getContextPath() + "/View/Blog.jsp");
        }else{
            List<BlogBean> blogs = (List<BlogBean>) request.getSession().getAttribute("blogs");
            deleteListElement(blogs,blogCreatAtAndName,bossBean.getName(),creatAt);
            request.getSession().setAttribute("blogs", blogs);
            response.sendRedirect(request.getContextPath() + "/View/Information.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request, response);
    }
    public void deleteListElement(List<BlogBean> blogs,String blogCreatAtAndName,String bossId,String creatAt ){
        for (int i = 0; i < blogs.size(); i++) {
            if (blogs.get(i).getCreatAtAndName().equals(blogCreatAtAndName)) {
                List<CommentBean> listCom = blogs.get(i).getListCom();
                for(int i1 = 0 ;i1 < listCom.size();i1++){
                    if(listCom.get(i).getCreatAt().equals(creatAt) && listCom.get(i).getBossId().equals(bossId)){
                        listCom.remove(i1);
                        i1--;
                        break;
                    }
                }
                break;
            }
        }
    }
}
