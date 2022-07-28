package front.shoppingcar;

import java.sql.*;

public class shoppingcar_post_JDBCAction {
    public int PostNumbyId(int shoppingcar_id,int num){
        PreparedStatement pSmt;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("数据库驱动记载成功！");
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动加载失败！");
            e.printStackTrace();
        }
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName = tb";
        try {
            Connection conn = DriverManager.getConnection(url,"DYL","FOREVERDYL121");
            System.out.println("数据库连接成功！");
            String sql = "update tb_gouwuche set num = ? where id = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setInt(1,num);
            pSmt.setInt(2,shoppingcar_id);
            pSmt.executeUpdate();
            pSmt.close();
            conn.close();
            return 1;
        } catch (SQLException e) {
            System.out.println("连接数据库失败！");
            e.printStackTrace();
        }
        return 0;
    }
}
