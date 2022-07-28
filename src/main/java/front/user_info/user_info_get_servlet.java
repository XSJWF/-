package front.user_info;

import front.UserBean.UserBean;
import net.sf.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "user_info_get_servlet", value = "/user_info_get_servlet")
public class user_info_get_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        UserBean user = new UserBean();
        user.setUsername(request.getParameter("username"));
        user = new user_info_get_JDBCAction().GetUserbyName(user);
        JSONObject json = JSONObject.fromObject(user);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
    }
}
