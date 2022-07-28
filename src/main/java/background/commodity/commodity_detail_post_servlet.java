package background.commodity;

import background.CommodityBean.CommodityBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "commodity_detail_post_servlet", value = "/commodity_detail_post_servlet")
public class commodity_detail_post_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        CommodityBean commodity = new CommodityBean();
        commodity.setShangpin_id(Integer.parseInt(request.getParameter("shangpin_id")));
        commodity.setShangpin_mingcheng(request.getParameter("shangpin_mingcheng"));
        commodity.setShangpin_addtime(request.getParameter("shangpin_addtime"));
        commodity.setShangpin_pinpai(request.getParameter("shangpin_pinpai"));
        commodity.setShangpin_shichangjia(request.getParameter("shangpin_shichangjia"));
        commodity.setShangpin_huiyuanjia(request.getParameter("shangpin_huiyuanjia"));
        commodity.setShangpin_cishu(Integer.parseInt(request.getParameter("shangpin_cishu")));
        commodity.setShangpin_shuliang(Integer.parseInt(request.getParameter("shangpin_shuliang")));
        commodity.setShangpin_tejia(Integer.parseInt(request.getParameter("shangpin_tejia")));
        commodity.setShangpin_tuijian(Integer.parseInt(request.getParameter("shangpin_tuijian")));
        commodity.setShangpin_xinghao(request.getParameter("shangpin_xinghao"));
        commodity.setShangpin_typeid(Integer.parseInt(request.getParameter("shangpin_typeid")));
        commodity.setShangpin_type2id(Integer.parseInt(request.getParameter("shangpin_type2id")));
        commodity.setShangpin_type3id(Integer.parseInt(request.getParameter("shangpin_type3id")));
        commodity.setShangpin_dengji(request.getParameter("shangpin_dengji"));
        commodity.setShangpin_jianjie(request.getParameter("shangpin_jianjie"));
        commodity.setShangpin_tupian(request.getParameter("shangpin_tupian"));
        int flag = new commodity_detail_post_JDBCAction().PostInfobyID(commodity);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(flag == 1){
            out.print("修改成功！");
        }else {
            out.print("修改失败！");
        }
    }
}
