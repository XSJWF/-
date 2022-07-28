package background.information;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "notice_delete_servlet", value = "/notice_delete_servlet")
public class notice_delete_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int flag = new notice_delete_JDBCAction().DeleteNoticebyID(Integer.parseInt(request.getParameter("notice_id")));
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(flag == 1){
            out.println("公告删除成功！");
        }else {
            out.println("公告删除失败！");
        }
    }
}
