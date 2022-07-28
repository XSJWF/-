package background.information;

import background.NoticeBean.NoticeBean;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class notice_add_JDBCAction {
    public int AddInfobyNotice(NoticeBean notice){
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
            String sql = "select max(id) as id from tb_gonggao";
            pSmt = conn.prepareStatement(sql);
            res = pSmt.executeQuery();
            if (res.next()){
                notice.setNotice_id(res.getInt("id")+1);
            }else {
                notice.setNotice_id(1);
            }
            LocalDateTime time = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            notice.setNotice_time(time.format(formatter));
            sql = "insert into tb_gonggao (id,title,content,time) values(?,?,?,?)";
            pSmt = conn.prepareStatement(sql);
            pSmt.setInt(1,notice.getNotice_id());
            pSmt.setString(2,notice.getNotice_title());
            pSmt.setString(3,notice.getNotice_content());
            pSmt.setString(4,notice.getNotice_time());
            pSmt.executeUpdate();
            res.close();
            pSmt.close();
            conn.close();
            return 1;
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
        return 0;
    }
}
