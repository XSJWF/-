package front.register;

import front.UserBean.UserBean;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "register_servlet", value = "/register_servlet")
public class register_servlet extends HttpServlet {
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
        user.setC_password(request.getParameter("c-password"));
        user.setTelephone(request.getParameter("telephone"));
        user.setEmail(request.getParameter("email"));
        user.setQuestion(request.getParameter("question"));
        user.setAnswer(request.getParameter("s-answer"));
        int flag = new register_JDBCAction().GetUserbyName(user);
        request.setAttribute("flag",flag);
        request.setAttribute("username",user.getUsername());
        PrintWriter out = response.getWriter();
        out.print(flag);
    }
}
