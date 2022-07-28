package background.commodity;

import background.commodity_CategoryBean.CategoryBean;
import net.sf.json.JSONArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "commodity_category_get_servlet", value = "/commodity_category_get_servlet")
public class commodity_category_get_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ArrayList<CategoryBean> categorylist1 = new commodity_category1_get_JDBCAction().GetInfobyCategory();
        ArrayList<CategoryBean> categorylist2 = new commodity_category2_get_JDBCAction().GetInfobyCategory();
        ArrayList<CategoryBean> categorylist3 = new commodity_category3_get_JDBCAction().GetInfobyCategory();
        JSONArray json1 = JSONArray.fromObject(categorylist1);
        JSONArray json2 = JSONArray.fromObject(categorylist2);
        JSONArray json3 = JSONArray.fromObject(categorylist3);
        JSONArray json = new JSONArray();
        json.add(json1);
        json.add(json2);
        json.add(json3);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
    }
}
