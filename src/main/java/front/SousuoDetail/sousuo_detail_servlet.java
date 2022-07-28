package front.SousuoDetail;

import background.CommodityBean.CommodityBean;
import net.sf.json.JSONArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "sousuo_detail_servlet", value = "/sousuo_detail_servlet")
public class sousuo_detail_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ArrayList<CommodityBean> shangpinlist = new sousuo_detail_JDBCActon().GetInfobyCommodity(request.getParameter("keyword"));
        JSONArray json = JSONArray.fromObject(shangpinlist);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
    }
}
