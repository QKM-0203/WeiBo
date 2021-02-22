package WeiBo.Servlet;

import WeiBo.Bean.BossBean;
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
            request.getSession().setAttribute("userInformation",InformationBean);
        }
          request.getRequestDispatcher("/View/Information.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request,response);
    }
}
