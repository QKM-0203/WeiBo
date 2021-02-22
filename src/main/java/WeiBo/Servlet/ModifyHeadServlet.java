package WeiBo.Servlet;

import WeiBo.Bean.*;
import WeiBo.Service.Imp.BlogServiceImp;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(value = "/modifyHeadServlet",name = "modifyHeadServlet")
public class ModifyHeadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
        servletFileUpload.setHeaderEncoding("utf-8");
        HttpSession session = request.getSession();
        BossBean boss = (BossBean) session.getAttribute("name");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            ArrayList<PictureBean> pictureBeans = new ArrayList<>();
            List<FileItem> fileItems = servletFileUpload.parseRequest(request);
            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()) {
                    String name = fileItem.getName();
                    String substring = name.substring(name.lastIndexOf("."));
                    String picName = new Date().getTime() +boss.getId()+substring;
                    String realPath = this.getServletContext().getRealPath("/head");
                    File file = new File(realPath);
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    File file1 = new File(realPath, picName);
                    //把上传文件的内容保存到指定文件
                    fileItem.write(file1);
                    fileItem.delete();
                    BlogServiceImp blogServiceImp = new BlogServiceImp();
                    blogServiceImp.modifyHead("/head/"+picName,boss.getId());
                    BossBean bossBean= (BossBean) request.getSession().getAttribute("name");
                    File file2 = new File(this.getServletContext().getRealPath(bossBean.getHead()));
                    if(!file2.getName().equals("default.jpg")){
                        file2.delete();
                    }
                    bossBean.setHead("/head/"+picName);
                    request.getSession().setAttribute("name",bossBean);

                    List<BlogBean> blogs = (List<BlogBean>)request.getSession().getAttribute("AllBlogs");
                    List<BlogBean> blogBeans = modifyHead(blogs, bossBean.getId(), bossBean.getHead());
                    request.getSession().setAttribute("AllBlogs",blogBeans);

                    List<BlogBean> blog = (List<BlogBean>)request.getSession().getAttribute("blogs");
                    List<BlogBean> blogBeans1 = modifyHead(blog, bossBean.getId(), bossBean.getHead());
                    request.getSession().setAttribute("blogs",blogBeans1);

                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doPost(request, response);
    }

    public List<BlogBean> modifyHead(List<BlogBean> blogs,String bossId, String head){
        for (BlogBean blog : blogs) {
            if(blog.getBossId().equals(bossId)){
                blog.setHead(head);
            }
            List<CommentBean> listCom = blog.getListCom();
            for (CommentBean commentBean : listCom) {
                if(commentBean.getBossId().equals(bossId)){
                    commentBean.setHead(head);
                }
            }
        }
        return blogs;
    }
}
