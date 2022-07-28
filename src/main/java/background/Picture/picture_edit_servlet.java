package background.Picture;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "picture_edit_servlet", value = "/picture_edit_servlet")
public class picture_edit_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int flag = new picture_edit_JDBCAction().EditInfobyID(Integer.parseInt(request.getParameter("picture_id")),request.getParameter("category"),request.getParameter("var"));
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(flag == 1){
            out.println("修改成功！");
        }else {
            out.println("修改失败！");
        }
    }
}
