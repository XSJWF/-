package background.PictureBean.person;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "person_user_delete_servlet", value = "/person_user_delete_servlet")
public class person_user_delete_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int flag = new person_user_delete_JDBCAction().DeletePersonbyID(Integer.parseInt(request.getParameter("person_id")));
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(flag == 1){
            out.println("删除成功！");
        }else {
            out.println("删除失败！");
        }
    }
}
