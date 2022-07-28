package background.PictureBean.person;

import background.PersonBean.PersonBean;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "person_admin_detail_servlet", value = "/person_admin_detail_servlet")
public class person_admin_detail_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        PersonBean person = new PersonBean();
        person.setPerson_name(request.getParameter("admin_name"));
        person = new person_admin_detail_JDBCAction().GetInfobyID(person);
        JSONObject json = JSONObject.fromObject(person);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(json);
    }
}
