package front.shoppingcar;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "shoppingcar_delete_servlet", value = "/shoppingcar_delete_servlet")
public class shoppingcar_delete_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int flag = new shoppingcar_delete_JDBCAction().DeletebyId(Integer.parseInt(request.getParameter("shoppingcar_id")));
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(flag == 1){
            out.print("成功移除！");
        }else {
            out.print("移除失败！");
        }
    }
}
