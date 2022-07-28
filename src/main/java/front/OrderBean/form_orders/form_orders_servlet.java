package front.OrderBean.form_orders;

import front.UserBean.UserBean;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "form_orders_servlet", value = "/form_orders_servlet")
public class form_orders_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        UserBean user = new UserBean();
        user.setUsername(request.getParameter("username"));
        user = new form_orders_JDBCAction().GetInfobyName(user);
        JSONObject json = JSONObject.fromObject(user);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
    }
}
