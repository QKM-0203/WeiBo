package WeiBo.Servlet;

import WeiBo.Bean.BlogBean;
import WeiBo.Bean.BossBean;
import WeiBo.Bean.InformationBean;
import WeiBo.Service.Imp.BlogServiceImp;
import WeiBo.Service.Imp.InformationServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/findInformationServlet",name = "findInformationServlet")
public class FindInformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        BossBean bossBean =(BossBean)session.getAttribute("name");
        InformationServiceImp userServiceImp = new InformationServiceImp();
        InformationBean information = userServiceImp.findInformation(bossBean.getName());
        request.getSession().setAttribute("userInformation",information);
        String id = request.getParameter("id");
        if(id.equals("look")){
            String size = request.getParameter("size");
            if(size.equals("own")){
                request.getRequestDispatcher("/findBlogServlet?size=own").forward(request,response);
            }else{
                request.getRequestDispatcher("/findBlogServlet?size=all").forward(request,response);
            }
        }
        else{

            request.getRequestDispatcher("/View/updateInformation.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request,response);
    }
}
