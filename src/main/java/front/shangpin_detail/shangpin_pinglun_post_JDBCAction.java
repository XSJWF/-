package front.shangpin_detail;

import background.CommentBean.CommentBean;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class shangpin_pinglun_post_JDBCAction {
    public int PostInfobyShangpin(CommentBean comment){
        PreparedStatement pSmt;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("数据库驱动加载成功！");
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动加载失败！");
            e.printStackTrace();
        }
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName = tb";
        try{
            Connection conn = DriverManager.getConnection(url,"DYL","FOREVERDYL121");
            System.out.println("数据库连接成功！");
            String sql = "select max(id) as id from tb_pinglun";
            pSmt = conn.prepareStatement(sql);
            ResultSet res = pSmt.executeQuery();
            if(res.next()){
                comment.setComment_id(res.getInt("id")+1);
            }else {
                comment.setComment_id(1);
            }
            sql = "select * from tb_user where name = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1,comment.getComment_username());
            res = pSmt.executeQuery();
            res.next();
            comment.setComment_userid(res.getInt("id"));
            LocalDateTime time = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            comment.setComment_time(time.format(formatter));
            sql = "insert into tb_pinglun (id,userid,spid,title,content,time) values(?,?,?,?,?,?)";
            pSmt = conn.prepareStatement(sql);
            pSmt.setInt(1,comment.getComment_id());
            pSmt.setInt(2,comment.getComment_userid());
            pSmt.setInt(3,comment.getComment_spid());
            pSmt.setString(4,comment.getComment_title());
            pSmt.setString(5,comment.getComment_content());
            pSmt.setString(6,comment.getComment_time());
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
