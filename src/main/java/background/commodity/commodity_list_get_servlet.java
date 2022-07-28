package background.commodity;

import background.CommodityBean.CommodityBean;
import net.sf.json.JSONArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "commodity_list_get_servlet", value = "/commodity_list_get_servlet")
public class commodity_list_get_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ArrayList<CommodityBean> shangpinlist = new commodity_list_get_JDBCAction().GetShangpinbyNull();
        JSONArray json = JSONArray.fromObject(shangpinlist);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
    }
}
