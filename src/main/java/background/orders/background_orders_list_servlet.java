package background.orders;

import background.OrderBean.OrderBean;
import net.sf.json.JSONArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "background_orders_list_servlet", value = "/background_orders_list_servlet")
public class background_orders_list_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ArrayList<OrderBean> orderlist = new background_orders_list_JDBCAction().GetInfobyOrder();
        JSONArray json = JSONArray.fromObject(orderlist);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
    }
}
