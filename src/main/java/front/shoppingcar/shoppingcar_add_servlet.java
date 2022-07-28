package front.shoppingcar;

import front.ShangpinBean.ShangpinBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "shoppingcar_add_servlet", value = "/shoppingcar_add_servlet")
public class shoppingcar_add_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ShangpinBean shangpin = new ShangpinBean();
        shangpin.setShangpin_id(Integer.parseInt(request.getParameter("shangpin_id")));
        shangpin.setNum(Integer.parseInt(request.getParameter("shangpin_num")));
        shangpin.setUser_name(request.getParameter("username"));
        int flag = new shoppingcar_add_JDBCAction().PostInfobyID(shangpin);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(flag == 1){
            out.println("添加购物车成功！");
        }else {
            out.println("添加购物车失败！");
        }
    }
}
