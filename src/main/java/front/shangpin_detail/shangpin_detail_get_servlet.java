package front.shangpin_detail;

import background.CommodityBean.CommodityBean;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "shangpin_detail_get_servlet", value = "/shangpin_detail_get_servlet")
public class shangpin_detail_get_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        CommodityBean commodity = new shangpin_detail_get_JDBCAction().GetInfobyID(Integer.parseInt(request.getParameter("shangpin_id")));
        JSONObject json = JSONObject.fromObject(commodity);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
    }
}
