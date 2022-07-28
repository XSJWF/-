package front.shangpin_detail;

import background.CommentBean.CommentBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "shangpin_pinglun_post_servlet", value = "/shangpin_pinglun_post_servlet")
public class shangpin_pinglun_post_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        CommentBean comment = new CommentBean();
        comment.setComment_username(request.getParameter("username"));
        comment.setComment_spid(Integer.parseInt(request.getParameter("shangpin_id")));
        comment.setComment_content(request.getParameter("comment_content"));
        comment.setComment_title(request.getParameter("comment_title"));
        int flag = new shangpin_pinglun_post_JDBCAction().PostInfobyShangpin(comment);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(flag == 1){
            out.println("评论成功！");
        }else {
            out.println("评论失败！");
        }
    }
}
