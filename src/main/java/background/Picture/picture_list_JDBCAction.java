package background.Picture;

import background.PictureBean.PictureBean;

import java.sql.*;
import java.util.ArrayList;

public class picture_list_JDBCAction {
    public ArrayList<PictureBean> GetInfobyPicture(){
        ArrayList<PictureBean> picturelist = new ArrayList<>();
        PreparedStatement pSmt;
        ResultSet res;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("数据库驱动加载成功！");
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动加载失败！");
            e.printStackTrace();
        }
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName = tb";
        try {
            Connection conn = DriverManager.getConnection(url,"DYL","FOREVERDYL121");
            System.out.println("数据库连接成功！");
            String sql = "select * from tb_guanggao";
            pSmt = conn.prepareStatement(sql);
            res = pSmt.executeQuery();
            while (res.next()){
                PictureBean picture = new PictureBean();
                picture.setPicture_id(res.getInt("id"));
                picture.setPicture_path(res.getString("path"));
                picture.setPicture_url(res.getString("url"));
                picture.setPicture_width(res.getString("width"));
                picture.setPicture_height(res.getString("height"));
                picturelist.add(picture);
            }
            res.close();
            pSmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
        return picturelist;
    }
}
