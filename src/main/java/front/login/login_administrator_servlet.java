package front.login;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "login_administrator_servlet", value = "/login_administrator_servlet")
public class login_administrator_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        int flag = new login_administrator_JDBCAction().getFlagbyadmin(request.getParameter("adminname"),request.getParameter("password"));
        PrintWriter out = response.getWriter();
        out.print(flag);
    }
}
