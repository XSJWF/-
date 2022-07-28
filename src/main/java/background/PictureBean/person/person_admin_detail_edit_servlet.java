package background.PictureBean.person;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "person_admin_detail_edit_servlet", value = "/person_admin_detail_edit_servlet")
public class person_admin_detail_edit_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int flag = new person_admin_detail_edit_JDBCAction().PostInfobyedit(request.getParameter("category"), Integer.parseInt(request.getParameter("person_id")),request.getParameter("var"));
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(flag == 1){
            out.println("修改成功！");
        }else {
            out.println("修改失败！");
        }
    }
}
