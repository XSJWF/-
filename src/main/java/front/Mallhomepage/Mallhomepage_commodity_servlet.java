package front.Mallhomepage;

import background.CommodityBean.CommodityBean;
import net.sf.json.JSONArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Mallhomepage_commodity_servlet", value = "/Mallhomepage_commodity_servlet")
public class Mallhomepage_commodity_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ArrayList<CommodityBean> commoditylist = new Mallhomepage_commodity_JDBCAction().GetInfobyCommodity();
        JSONArray json = JSONArray.fromObject(commoditylist);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
    }
}
