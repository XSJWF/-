package background.commodity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "commodity_category_edit_servlet", value = "/commodity_category_edit_servlet")
public class commodity_category_edit_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int flag = new commodity_category_edit_JDBCAction().PostInfobyEdit(request.getParameter("category"),request.getParameter("second_category"), Integer.parseInt(request.getParameter("id")),request.getParameter("var"));
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(flag == 1){
            out.print("修改成功！");
        }else {
            out.print("修改失败！");
        }
    }
}
