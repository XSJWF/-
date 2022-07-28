package background.information;

import background.CommentBean.CommentBean;
import net.sf.json.JSONArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "commodity_comment_list_servlet", value = "/commodity_comment_list_servlet")
public class commodity_comment_list_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ArrayList<CommentBean> commentlist = new commodity_comment_list_JDBCAction().GetInfobyComment();
        JSONArray json = JSONArray.fromObject(commentlist);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
    }
}
