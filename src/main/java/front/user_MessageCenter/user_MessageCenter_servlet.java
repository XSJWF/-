package front.user_MessageCenter;

import front.MessageBean.MessageBean;
import net.sf.json.JSONArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "user_MessageCenter_servlet", value = "/user_MessageCenter_servlet")
public class user_MessageCenter_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        ArrayList<MessageBean> messagelist = new user_MessageCenter_JDBCAction().GetMessagebyName(request.getParameter("username"));
        JSONArray json = JSONArray.fromObject(messagelist);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
    }
}
