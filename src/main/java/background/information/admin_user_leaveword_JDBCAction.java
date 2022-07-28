package background.information;

import front.MessageBean.MessageBean;

import java.sql.*;
import java.util.ArrayList;

public class admin_user_leaveword_JDBCAction {
    public ArrayList<MessageBean> GetMessage(){
        PreparedStatement pSmt;
        ArrayList<MessageBean> messagelist = new ArrayList<>();
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
            String sql = "select * from tb_leaveword where huifu = 0";
            pSmt = conn.prepareStatement(sql);
            res = pSmt.executeQuery();
            while (res.next()){
                MessageBean message = new MessageBean();
                message.setUser_id(res.getInt("userid"));
                message.setLeaveword_id(res.getInt("id"));
                message.setUser_time(res.getString("time"));
                message.setUser_title(res.getString("title"));
                message.setUser_content(res.getString("content"));
                String sql1 = "select * from tb_user where id = ?";
                PreparedStatement pSmt1 = conn.prepareStatement(sql1);
                pSmt1.setInt(1,res.getInt("userid"));
                ResultSet res1 = pSmt1.executeQuery();
                res1.next();
                message.setUser_name(res1.getString("name"));
                if (res.getInt("huifu") == 1){
                    message.setZt("已回复");
                }else{
                    message.setZt("点击回复");
                }
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
