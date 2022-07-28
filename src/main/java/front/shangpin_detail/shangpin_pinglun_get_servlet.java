package front.shangpin_detail;

import background.CommentBean.CommentBean;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "shangpin_pinglun_get_servlet", value = "/shangpin_pinglun_get_servlet")
public class shangpin_pinglun_get_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ArrayList<CommentBean> commentlist = new shangpin_pinglun_get_JDBCAction().GetCommentbyAll(Integer.parseInt(request.getParameter("shangpin_id")));
        JSONArray json = JSONArray.fromObject(commentlist);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
    }
}
