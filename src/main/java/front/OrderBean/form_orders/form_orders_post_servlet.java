package front.OrderBean.form_orders;

import front.OrderBean.OrderBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "form_orders_post_servlet", value = "/form_orders_post_servlet")
public class form_orders_post_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        OrderBean order = new OrderBean();
        order.setShouhuoren(request.getParameter("shouhuoren"));
        order.setSex(request.getParameter("sex"));
        order.setYoubian(request.getParameter("youbian"));
        order.setTel(request.getParameter("tel"));
        order.setDizhi(request.getParameter("dizhi"));
        order.setLeaveword(request.getParameter("leaveword"));
        order.setTotal(request.getParameter("total"));
        order.setXiadanren(request.getParameter("xiadanren"));
        int flag = new form_orders_post_JDBCAction().PostDingdanbyInfo(order);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(flag == 1){
            out.print("订单提交成功！");
        }else {
            out.print("订单提交失败！");
        }
    }
}
