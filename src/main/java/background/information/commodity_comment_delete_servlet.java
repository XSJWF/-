package background.information;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "commodity_comment_delete_servlet", value = "/commodity_comment_delete_servlet")
public class commodity_comment_delete_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int flag = new commodity_comment_delete_JDBCAction().DeleteCommentbyID(Integer.parseInt(request.getParameter("comment_id")));
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(flag == 1){
            out.println("评论删除成功！");
        }else {
            out.println("评论删除失败！");
        }
    }
}
