package WeiBo.Servlet;

import WeiBo.Bean.BossBean;
import WeiBo.Bean.SumBean;
import WeiBo.Service.Imp.BlogServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addAttentionServlet",name = "addAttentionServlet")
public class AddAttentionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String peopleId = request.getParameter("peopleId");
        System.out.println(peopleId);
        BossBean bossBean = (BossBean) request.getSession().getAttribute("name");
        SumBean sum = (SumBean) request.getSession().getAttribute("sum");
        BlogServiceImp blogServiceImp = new BlogServiceImp();
        blogServiceImp.addAttentions(bossBean.getId(), peopleId);
        sum.setAttention(sum.getAttention()+1);
        request.getSession().setAttribute("sum",sum);
        request.getRequestDispatcher("View/Blog.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request, response);
    }
}
