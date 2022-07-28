package background.PictureBean.person;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "person_user_dongjie_servlet", value = "/person_user_dongjie_servlet")
public class person_user_dongjie_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int person_dongjie = Integer.parseInt(request.getParameter("person_dongjie"));
        int flag = new person_user_dongjie_JDBCAction().DongjiebyID(Integer.parseInt(request.getParameter("person_id")),person_dongjie);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(flag == 1){
            if (person_dongjie == 1){
                out.println("解冻成功！");
            }else {
                out.println("冻结成功！");
            }
        }else {
            if (person_dongjie == 1){
                out.println("解冻失败！");
            }else {
                out.println("冻结失败！");
            }
        }
    }
}
