package front.shoppingcar;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "shoppingcar_post_servlet", value = "/shoppingcar_post_servlet")
public class shoppingcar_post_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println(request.getParameter("shoppingcar_id")+"\n"+request.getParameter("num"));
        int flag = new shoppingcar_post_JDBCAction().PostNumbyId(Integer.parseInt(request.getParameter("shoppingcar_id")),Integer.parseInt(request.getParameter("num")));
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(flag == 1){
            out.print("修改成功！");
        }else {
            out.print("修改失败！");
        }
    }
}
