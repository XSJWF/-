package background.orders;

import background.OrderBean.OrderBean;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "background_orders_sousuo_servlet", value = "/background_orders_sousuo_servlet")
public class background_orders_sousuo_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        OrderBean order = new background_orders_sousuo_JDBCAction().GetOrderbyID(request.getParameter("dingdanhao"));
        JSONArray json = JSONArray.fromObject(order);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(json);
    }
}
