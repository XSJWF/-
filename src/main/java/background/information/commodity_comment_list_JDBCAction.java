package background.information;

import background.CommentBean.CommentBean;

import java.sql.*;
import java.util.ArrayList;

public class commodity_comment_list_JDBCAction {
    public ArrayList<CommentBean> GetInfobyComment(){
        ArrayList<CommentBean> commentlist = new ArrayList<>();
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
            String sql = "select * from tb_pinglun";
            pSmt = conn.prepareStatement(sql);
            res = pSmt.executeQuery();
            while(res.next()){
                CommentBean comment = new CommentBean();
                comment.setComment_id(res.getInt("id"));
                comment.setComment_userid(res.getInt("userid"));
                comment.setComment_spid(res.getInt("spid"));
                comment.setComment_title(res.getString("title"));
                comment.setComment_content(res.getString("content"));
                comment.setComment_time(res.getString("time"));
                commentlist.add(comment);
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
