package front.User_leaveword;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "user_leaveword_servlet", value = "/user_leaveword_servlet")
public class user_leaveword_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int flag = new user_leaveword_JDBCAction().PostWordbyId(request.getParameter("username"),request.getParameter("title"),request.getParameter("content"));
        PrintWriter out = response.getWriter();
        out.print(flag);
    }
}
