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
         /*
        String bossId = request.getParameter("bossId");
        System.out.println(bossId);
        String name = request.getParameter("name");
        System.out.println(name);
        String age = request.getParameter("age");
        String qq = request.getParameter("qq");
        String mail = request.getParameter("mail");
        String sex = request.getParameter("sex");
        String birth = request.getParameter("birth");
        String own = request.getParameter("own");
        String birthArea = request.getParameter("birthArea");
       InformationBean.setBossId(bossId);
        InformationBean.setName(name);
        InformationBean.setAge(age);
        InformationBean.setBirth(birth);
        InformationBean.setMail(mail);
        InformationBean.setQq(qq);
        InformationBean.setSex(sex);
        InformationBean.setBirthArea(birthArea);
        InformationBean.setOwn(own);

          */
        InformationServiceImp informationServiceImp = new InformationServiceImp();
        informationServiceImp.updateInformation(InformationBean);
        request.setAttribute("userInformation",InformationBean);
        request.getRequestDispatcher("/View/Information.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request,response);
    }
}
