package background.orders;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "background_orders_detail_servlet", value = "/background_orders_detail_servlet")
public class background_orders_detail_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int flag = new background_orders_detail_JDBCAction().PostInfobyzt(Integer.parseInt(request.getParameter("order_id")),request.getParameter("order_zt"));
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(flag == 1){
            out.println("订单状态修改成功！");
        }else {
            out.println("订单状态修改失败！");
        }
    }
}
