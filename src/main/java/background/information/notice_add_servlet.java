package background.information;

import background.NoticeBean.NoticeBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "notice_add_servlet", value = "/notice_add_servlet")
public class notice_add_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        NoticeBean notice = new NoticeBean();
        notice.setNotice_title(request.getParameter("notice_title"));
        notice.setNotice_content(request.getParameter("notice_content"));
        int flag = new notice_add_JDBCAction().AddInfobyNotice(notice);
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        if(flag == 1){
            out.println("新增公告成功！");
        }else {
            out.println("新增公告失败！");
        }
    }
}
