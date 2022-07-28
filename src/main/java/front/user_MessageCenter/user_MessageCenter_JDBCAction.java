package front.user_MessageCenter;

import front.MessageBean.MessageBean;

import java.sql.*;
import java.util.ArrayList;

public class user_MessageCenter_JDBCAction {
    public ArrayList<MessageBean> GetMessagebyName(String name){
        ArrayList<MessageBean> messagelist = new ArrayList<>();
        PreparedStatement pSmt;
        ResultSet res;
        try{
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
            String sql = "select * from tb_user where name = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1,name);
            res = pSmt.executeQuery();
            res.next();
            int userid = res.getInt("id");
            sql = "select * from tb_huifu where userid = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setInt(1,userid);
            res = pSmt.executeQuery();
            while (res.next()) {
                MessageBean message = new MessageBean();
                message.setAdmin_id(res.getInt("id"));
                message.setAdmin_time(res.getString("time"));
                message.setAdmin_content(res.getString("content"));
                message.setAdmin_title(res.getString("title"));
                String zt = "";
                if(res.getInt("yidu") == 1){
                    zt += "已读";
                }else{
                    zt += "确认已读";
                }
                message.setZt(zt);
                int leavewordid;
                leavewordid = res.getInt("leavewordid");
                String sql1 = "select * from tb_leaveword where id = ?";
                PreparedStatement pSmt1 = conn.prepareStatement(sql1);
                pSmt1.setInt(1,leavewordid);
                ResultSet res1 = pSmt1.executeQuery();
                res1.next();
                message.setUser_time(res1.getString("time"));
                message.setUser_content(res1.getString("content"));
                messagelist.add(message);
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
        return messagelist;
    }
}
