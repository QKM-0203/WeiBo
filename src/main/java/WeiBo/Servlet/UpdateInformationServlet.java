package WeiBo.Servlet;

import WeiBo.Bean.BlogBean;
import WeiBo.Bean.BossBean;
import WeiBo.Bean.CommentBean;
import WeiBo.Service.Imp.InformationServiceImp;
import WeiBo.Bean.InformationBean;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet(value = "/updateInformationServlet",name = "updateInformationServlet")
public class UpdateInformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InformationBean InformationBean = new InformationBean();
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(InformationBean,parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        InformationServiceImp informationServiceImp = new InformationServiceImp();
        int i = informationServiceImp.updateInformation(InformationBean);

        if(i  == 0){
            request.setAttribute("ku","该昵称已存在");
        }
        else{
            BossBean bossBean = (BossBean) request.getSession().getAttribute("name");
            bossBean.setName(InformationBean.getName());
            request.getSession().setAttribute("name",bossBean);

            List<BlogBean> blogs = (List<BlogBean>)request.getSession().getAttribute("AllBlogs");
            List<BlogBean> blogBeans = modifyName(blogs, bossBean.getId(), InformationBean.getName());
            request.getSession().setAttribute("AllBlogs",blogBeans);

            List<BlogBean> blog = (List<BlogBean>)request.getSession().getAttribute("blogs");
            List<BlogBean> blogBeans1 = modifyName(blog, bossBean.getId(), InformationBean.getName());
            request.getSession().setAttribute("blogs",blogBeans1);

            request.getSession().setAttribute("userInformation",InformationBean);
        }
          request.getRequestDispatcher("/View/Information.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request,response);
    }

    public List<BlogBean> modifyName(List<BlogBean> blogs,String bossId, String name){
        for (BlogBean blog : blogs) {
            if(blog.getBossId().equals(bossId)){
                blog.setName(name);
            }
            List<CommentBean> listCom = blog.getListCom();
            for (CommentBean commentBean : listCom) {
                if(commentBean.getBossId().equals(bossId)){
                    commentBean.setName(name);
                }
            }
        }
        return blogs;
    }
}
