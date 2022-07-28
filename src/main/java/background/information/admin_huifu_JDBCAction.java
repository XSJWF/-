package background.information;

import front.MessageBean.MessageBean;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class admin_huifu_JDBCAction {
    public int PostMessage(MessageBean message){
        PreparedStatement pSmt;
        int flag = 0;
        ResultSet res;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("加载数据库驱动成功！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("加载数据库驱动成功！");
        }
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName = tb";
        try {
            Connection conn = DriverManager.getConnection(url,"DYL","FOREVERDYL121");
            System.out.println("连接数据库成功！");
            String sql = "select max(id) as id from tb_huifu";
            pSmt = conn.prepareStatement(sql);
            res = pSmt.executeQuery();
            int id;
            if (res.next()){
                id = res.getInt("id") + 1;
            }else {
                id = 1;
            }
            LocalDateTime time = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            message.setAdmin_time(time.format(formatter));
            sql = "insert into tb_huifu (id,userid,leavewordid,title,content,time,yidu) values (?,?,?,?,?,?,0)";
            pSmt = conn.prepareStatement(sql);
            pSmt.setInt(1,id);
            pSmt.setInt(2,message.getUser_id());
            pSmt.setInt(3,message.getLeaveword_id());
            pSmt.setString(4,message.getAdmin_title());
            pSmt.setString(5,message.getAdmin_content());
            pSmt.setString(6,message.getAdmin_time());
            pSmt.executeUpdate();
            sql = "update tb_leaveword set huifu = 1 where id = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setInt(1,message.getLeaveword_id());
            pSmt.executeUpdate();
            flag = 1;
            res.close();
            pSmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("连接数据库失败！");
        }
        return flag;
    }
}
