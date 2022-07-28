package front.user_MessageCenter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "user_postMessage_servlet", value = "/user_postMessage_servlet")
public class user_postMessage_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int flag = new user_postMessage_JDBCAction().PostZtbyId(Integer.parseInt(request.getParameter("admin_id")));
        String text = "";
        if (flag == 1){
            text += "已读";
        }else {
            text += "确认失败";
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(text);
    }
}
