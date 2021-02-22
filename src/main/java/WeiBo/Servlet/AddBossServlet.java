package WeiBo.Servlet;

import WeiBo.Service.Imp.InformationServiceImp;
import WeiBo.Service.Imp.bossServiceImp;
import WeiBo.Bean.BossBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(value = "/addBossServlet",name = "addBossServlet")
public class AddBossServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Id = request.getParameter("Id");
        String password = request.getParameter("password");
        String password1 = request.getParameter("password1");
        if(password.equals("")|| password1.equals("")){
            request.setAttribute("ku","密码不能为空");
        } else if(password.equals(password1)){
            bossServiceImp bossServiceImp = new bossServiceImp();
            BossBean bossBean = new BossBean();
            bossBean.setPassword(password);
            bossBean.setId(Id);
            long time = new Date().getTime();
            bossBean.setName("用户"+time);
            int i = bossServiceImp.addBoss(bossBean);
            if(i == 1){
                InformationServiceImp informationServiceImp = new InformationServiceImp();
                informationServiceImp.addInformation(Id,bossBean.getName());
                request.setAttribute("ku","注册成功");
            }else{
                request.setAttribute("ku","该用户已被注册");
            }
        }else{
            request.setAttribute("ku","两次密码不一致");
        }
        request.getRequestDispatcher("/View/registration.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
