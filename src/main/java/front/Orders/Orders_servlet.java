package front.Orders;

import front.OrderBean.OrderBean;
import net.sf.json.JSONArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Orders_servlet", value = "/Orders_servlet")
public class Orders_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        ArrayList<OrderBean> OrderList = new Orders_JDBCAction().GetOrderbyName(request.getParameter("xiadanren"));
        JSONArray json = JSONArray.fromObject(OrderList);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
    }
}
