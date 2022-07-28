package background.commodity;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "commodity_list_delete_servlet", value = "/commodity_list_delete_servlet")
public class commodity_list_delete_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int flag = new commodity_list_delete_JDBCAction().DeletebyID(Integer.parseInt(request.getParameter("shangpin_id")));
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(flag == 1){
            out.print("下架成功！");
        }else {
            out.print("下架失败！");
        }
    }
}
