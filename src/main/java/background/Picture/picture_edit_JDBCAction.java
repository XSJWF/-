package background.Picture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class picture_edit_JDBCAction {
    public int EditInfobyID(int picture_id,String category,String var){
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
            String sql = "update tb_guanggao set "+category+" = "+var+" where id = "+picture_id;
            pSmt = conn.prepareStatement(sql);
            pSmt.executeUpdate();
            pSmt.close();
            conn.close();
            return 1;
        } catch (SQLException e) {
            System.out.println("数据库加载失败！");
            e.printStackTrace();
        }
        return 0;
    }
}
