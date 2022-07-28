package front.shoppingcar;

import java.sql.*;

public class shoppingcar_clear_JDBCAction {
    public int Clear(String username){
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
            String sql = "select * from tb_user where name = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1,username);
            ResultSet res = pSmt.executeQuery();
            res.next();
            int user_id = res.getInt("id");
            sql = "delete from tb_gouwuche where uid = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setInt(1,user_id);
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
