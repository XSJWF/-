package front.login;

import front.UserBean.UserBean;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "login_user_servlet", value = "/login_user_servlet")
public class login_user_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        UserBean user = new UserBean();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        int flag = new login_user_JDBCAction().GetUserbyUserName(user);
        PrintWriter out = response.getWriter();
        out.print(flag);
    }
}
