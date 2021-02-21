package WeiBo.Servlet;

import WeiBo.Bean.BlogBean;
import WeiBo.Bean.BossBean;
import WeiBo.Bean.PictureBean;
import WeiBo.Bean.SumBean;
import WeiBo.Service.BlogService;
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

@WebServlet(value = "/addBlogServlet",name = "addBlogServlet")
public class AddBlogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date d = new Date();
        ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
        servletFileUpload.setHeaderEncoding("utf-8");
        String userImg = "userImg";
        HttpSession session = request.getSession();
        BossBean boss = (BossBean)session.getAttribute("name");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
        BlogBean blogBean = new BlogBean();
        blogBean.setBossId(boss.getName());
        blogBean.setCreatAtAndName(dateNowStr+boss.getName());
        try {
            ArrayList<PictureBean> pictureBeans = new ArrayList<>();
            List<FileItem> fileItems = servletFileUpload.parseRequest(request);
            for (FileItem fileItem : fileItems) {
                if(!fileItem.isFormField()){
                    String name = fileItem.getName();
                    String substring = name.substring(name.lastIndexOf("."));
                    String picName = new Date().getTime()+substring;

                    String realPath = this.getServletContext().getRealPath("/userImg");
                    PictureBean pictureBean = new PictureBean();
                    pictureBean.setPictureUri(realPath.substring(60)+"/"+picName);
                    pictureBean.setCreatAtAndName(dateNowStr+boss.getName());
                    pictureBeans.add(pictureBean);
                    File file = new File(realPath);
                    if(!file.exists()){
                        file.mkdir();
                    }
                    File file1 = new File(realPath,picName);
                    //把上传文件的内容保存到指定文件
                    fileItem.write(file1);
                    fileItem.delete();

                }else{
                        String think = fileItem.getString("utf-8");
                        if (think != null && !think.equals("0")) {
                            blogBean.setThink(think);
                        }
                }
            }
            blogBean.setListPic(pictureBeans);
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        BlogService blogServiceImp = new BlogServiceImp();
        blogServiceImp.addBlog(blogBean);
        SumBean sum = (SumBean) request.getSession().getAttribute("sum");
        sum.setTotalBlogs(sum.getTotalBlogs()+1);
        request.getSession().setAttribute("sum",sum);
        List<BlogBean> blogs = (List<BlogBean>)request.getSession().getAttribute("AllBlogs");
        blogs.add(0,blogBean);
        request.getSession().setAttribute("AllBlogs",blogs);
        response.sendRedirect(request.getContextPath()+"/View/Blog.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
