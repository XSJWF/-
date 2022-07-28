package background.commodity;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "commodity_category_delete_servlet", value = "/commodity_category_delete_servlet")
public class commodity_category_delete_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int flag = new commodity_category_delete_JDBCAction().DeleteInfobyID(request.getParameter("category"), Integer.parseInt(request.getParameter("id")));
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(flag == 1){
            out.print("删除成功！");
        }else {
            out.print("删除失败！");
        }
    }
}
