package background.information;

import background.NoticeBean.NoticeBean;
import net.sf.json.JSONArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "notice_list_servlet", value = "/notice_list_servlet")
public class notice_list_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ArrayList<NoticeBean> noticelist = new notice_list_JDBCAction().GetInfobyNotice();
        JSONArray json = JSONArray.fromObject(noticelist);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
    }
}
