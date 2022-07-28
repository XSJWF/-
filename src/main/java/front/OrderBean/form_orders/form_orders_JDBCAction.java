package front.OrderBean.form_orders;

import front.UserBean.UserBean;

import java.sql.*;

public class form_orders_JDBCAction {
    public UserBean GetInfobyName(UserBean user){
        PreparedStatement pSmt;
        ResultSet res;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("数据库驱动加载成功！");
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动记载失败！");
            e.printStackTrace();
        }
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName = tb";
        try {
            Connection conn = DriverManager.getConnection(url,"DYL","FOREVERDYL121");
            System.out.println("数据库连接成功！");
            String sql = "select * from tb_user where name = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1,user.getUsername());
            res = pSmt.executeQuery();
            res.next();
            user.setYoubian(res.getString("youbian"));
            user.setTelephone(res.getString("tel"));
            user.setDizhi(res.getString("dizhi"));
            res.close();
            pSmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
        return user;
    }
}
