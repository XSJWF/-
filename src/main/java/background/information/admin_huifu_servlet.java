package background.information;

import front.MessageBean.MessageBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "admin_huifu_servlet", value = "/admin_huifu_servlet")
public class admin_huifu_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        MessageBean message = new MessageBean();
        message.setUser_id(Integer.parseInt(request.getParameter("user_id")));
        message.setLeaveword_id(Integer.parseInt(request.getParameter("leaveword_id")));
        message.setAdmin_title(request.getParameter("admin_title"));
        message.setAdmin_content(request.getParameter("admin_content"));
        int flag = new admin_huifu_JDBCAction().PostMessage(message);
        String text;
        if(flag == 1){
            text = "回复成功！";
        }else {
            text = "回复失败！";
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(text);
    }
}
