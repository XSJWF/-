package front.login;

import java.sql.*;
import java.util.Objects;

public class login_administrator_JDBCAction {
    public int getFlagbyadmin(String adminname,String password){
        PreparedStatement pSmt;
        ResultSet res;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("数据库驱动加载成功！");
        }catch (ClassNotFoundException e){
            System.out.println("数据库驱动加载失败！");
            e.printStackTrace();
        }
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName = tb";
        try{
            Connection conn = DriverManager.getConnection(url, "DYL", "FOREVERDYL121");
            System.out.println("数据库连接成功！");
            String sql = "select * from tb_admin where name = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1,adminname);
            res = pSmt.executeQuery();
            if(!res.next()){
                return 0;//用户不存在返回0
            } else {
                if(Objects.equals(password, res.getString("pwd"))){
                    return 1;//登录成功返回1
                }else {
                    return 2;//密码错误返回2
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 4;//出现未知错误返回4
    }
}
