package background.information;

import background.NoticeBean.NoticeBean;

import java.sql.*;
import java.util.ArrayList;

public class notice_list_JDBCAction {
    public ArrayList<NoticeBean> GetInfobyNotice(){
        ArrayList<NoticeBean> noticelist = new ArrayList<>();
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
            String sql = "select * from tb_gonggao";
            pSmt = conn.prepareStatement(sql);
            res = pSmt.executeQuery();
            while (res.next()){
                NoticeBean notice = new NoticeBean();
                notice.setNotice_id(res.getInt("id"));
                notice.setNotice_time(res.getString("time"));
                notice.setNotice_title(res.getString("title"));
                notice.setNotice_content(res.getString("content"));
                noticelist.add(notice);
            }
            res.close();
            pSmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
        return noticelist;
    }
}
