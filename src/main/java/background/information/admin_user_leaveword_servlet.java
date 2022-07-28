package background.information;

import front.MessageBean.MessageBean;
import net.sf.json.JSONArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "admin_user_leaveword_servlet", value = "/admin_user_leaveword_servlet")
public class admin_user_leaveword_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ArrayList<MessageBean> messagelist = new admin_user_leaveword_JDBCAction().GetMessage();
        JSONArray json = JSONArray.fromObject(messagelist);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
    }
}
