package background.commodity;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "commodity_category_add_servlet", value = "/commodity_category_add_servlet")
public class commodity_category_add_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int flag = new commodity_category_add_JDBCAction().InsertInfobyadd(request.getParameter("category"),request.getParameter("typename"),request.getParameter("typeid"));
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(flag == 1){
            out.print("新增成功！");
        }else {
            out.print("新增失败！");
        }
    }
}
