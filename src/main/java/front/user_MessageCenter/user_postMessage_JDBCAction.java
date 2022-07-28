package front.user_MessageCenter;

import java.sql.*;

public class user_postMessage_JDBCAction {
    public int PostZtbyId(int id){
        PreparedStatement pSmt;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("加载数据库驱动成功！");
        } catch (ClassNotFoundException e) {
            System.out.println("加载数据库驱动失败！");
            e.printStackTrace();
        }
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName = tb";
        try {
            Connection conn = DriverManager.getConnection(url,"DYL","FOREVERDYL121");
            System.out.println("连接数据库成功！");
            String sql = "update tb_huifu set yidu = 1  where id = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setInt(1,id);
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
