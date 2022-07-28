package front.shoppingcar;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "shoppingcar_clear_servlet", value = "/shoppingcar_clear_servlet")
public class shoppingcar_clear_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int flag = new shoppingcar_clear_JDBCAction().Clear(request.getParameter("username"));
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(flag == 1){
            out.print("成功清空购物车！");
        }else {
            out.print("清空购物车失败！");
        }
    }
}
