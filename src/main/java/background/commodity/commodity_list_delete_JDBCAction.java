package background.commodity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class commodity_list_delete_JDBCAction {
    public int DeletebyID(int shangpin_id){
        PreparedStatement pSmt;
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
            String sql = "delete from tb_shangpin where id = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setInt(1,shangpin_id);
            pSmt.executeUpdate();
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
