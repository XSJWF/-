package front.user_info;

import front.UserBean.UserBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "user_info_post_servlet", value = "/user_info_post_servlet")
public class user_info_post_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        UserBean user = new UserBean();
        user.setUsername(request.getParameter("username"));
        user.setTelephone(request.getParameter("telephone"));
        user.setEmail(request.getParameter("email"));
        user.setQq(request.getParameter("QQ"));
        user.setDizhi(request.getParameter("dizhi"));
        user.setYoubian(request.getParameter("youbian"));
        user.setTruename(request.getParameter("truename"));
        String rename = request.getParameter("rename");
        int flag = new user_info_post_JDBCAction().PostUserbyUser(user,rename);
        response.getWriter().print(flag);
    }
}
