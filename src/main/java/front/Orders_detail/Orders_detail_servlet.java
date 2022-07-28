package front.Orders_detail;

import front.OrderBean.OrderBean;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Orders_detail_servlet", value = "/Orders_detail_servlet")
public class Orders_detail_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        OrderBean order = new Orders_detail_JDBCAction().GetOrderbydingdanhao(request.getParameter("dingdanhao"));
        JSONObject json = JSONObject.fromObject(order);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
    }
}
