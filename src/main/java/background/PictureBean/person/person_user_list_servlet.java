package background.PictureBean.person;

import background.PersonBean.PersonBean;
import net.sf.json.JSONArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "person_user_list_servlet", value = "/person_user_list_servlet")
public class person_user_list_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ArrayList<PersonBean> personlist = new person_user_list_JDBCAction().GetInfobyPerson();
        JSONArray json = JSONArray.fromObject(personlist);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
    }
}
