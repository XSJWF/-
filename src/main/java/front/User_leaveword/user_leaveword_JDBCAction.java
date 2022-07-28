package front.User_leaveword;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class user_leaveword_JDBCAction {
    public int PostWordbyId(String username,String title,String content){
        int flag = 0;
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
        try{
            Connection conn = DriverManager.getConnection(url,"DYL","FOREVERDYL121");
            System.out.println("数据库连接成功！");
            String sql = "select * from tb_user where name = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1,username);
            res = pSmt.executeQuery();
            res.next();
            int id;
            int userid = res.getInt("id");
            sql = "select max(id) as id from tb_leaveword";
            pSmt = conn.prepareStatement(sql);
            res = pSmt.executeQuery();
            if (res.next()){
                id = res.getInt("id")+1;
            }else{
                id = 1;
            }
            sql = "insert into tb_leaveword (id,userid,title,content,time,huifu) values(?,?,?,?,?,?)";
            pSmt = conn.prepareStatement(sql);
            pSmt.setInt(1,id);
            pSmt.setInt(2,userid);
            pSmt.setString(3,title);
            pSmt.setString(4,content);
            LocalDateTime time = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            pSmt.setString(5,time.format(formatter));
            pSmt.setInt(6,0);
            pSmt.executeUpdate();
            flag = 1;
            res.close();
            pSmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
        return flag;
    }
}
