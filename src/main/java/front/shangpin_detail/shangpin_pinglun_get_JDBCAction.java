package front.shangpin_detail;

import background.CommentBean.CommentBean;

import java.sql.*;
import java.util.ArrayList;

public class shangpin_pinglun_get_JDBCAction {
    public ArrayList<CommentBean> GetCommentbyAll(int shangpin_id){
        ArrayList<CommentBean> commentlist = new ArrayList<>();
        PreparedStatement pSmt,pSmt1;
        ResultSet res,res1;
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
            String sql = "select * from tb_pinglun where spid = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setInt(1,shangpin_id);
            res = pSmt.executeQuery();
            while(res.next()){
                CommentBean comment = new CommentBean();
                String sql1 = "select * from tb_user where id = ?";
                pSmt1 = conn.prepareStatement(sql1);
                pSmt1.setInt(1,res.getInt("userid"));
                res1 = pSmt1.executeQuery();
                res1.next();
                comment.setComment_username(res1.getString("name"));
                comment.setComment_id(res.getInt("id"));
                comment.setComment_userid(res.getInt("userid"));
                comment.setComment_spid(res.getInt("spid"));
                comment.setComment_title(res.getString("title"));
                comment.setComment_content(res.getString("content"));
                comment.setComment_time(res.getString("time"));
                commentlist.add(comment);
                res1.close();
                pSmt1.close();
            }
            res.close();
            pSmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
        return commentlist;
    }
}
