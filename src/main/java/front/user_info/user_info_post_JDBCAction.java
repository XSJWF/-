package front.user_info;

import front.UserBean.UserBean;

import java.sql.*;

public class user_info_post_JDBCAction {
    public int PostUserbyUser(UserBean user, String rename){
        int flag = 0;
        PreparedStatement pSmt;
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
            String sql = "update tb_user set name = ?,tel = ?,email = ?,qq = ?,dizhi = ?,youbian = ?,truename = ? where name = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1,user.getUsername());
            pSmt.setString(2,user.getTelephone());
            pSmt.setString(3,user.getEmail());
            pSmt.setString(4,user.getQq());
            pSmt.setString(5,user.getDizhi());
            pSmt.setString(6,user.getYoubian());
            pSmt.setString(7,user.getTruename());
            pSmt.setString(8,rename);
            pSmt.executeUpdate();
            flag = 1;
            pSmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
        return flag;
    }
}
