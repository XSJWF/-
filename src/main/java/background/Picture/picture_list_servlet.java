package background.Picture;

import background.PictureBean.PictureBean;
import net.sf.json.JSONArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "picture_list_servlet", value = "/picture_list_servlet")
public class picture_list_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ArrayList<PictureBean> picturelist = new picture_list_JDBCAction().GetInfobyPicture();
        JSONArray json = JSONArray.fromObject(picturelist);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
    }
}
