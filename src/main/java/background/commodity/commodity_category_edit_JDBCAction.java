package background.commodity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class commodity_category_edit_JDBCAction {
    public int PostInfobyEdit(String category,String second_category,int id,String var){
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
            String sql;
            if (Objects.equals(second_category, "typename")) {
                sql = "update tb_type" + category + " set " + second_category + "='" + var + "' where id=" + id;
            }else {
                sql = "update tb_type" + category + " set " + second_category + "=" + var + " where id=" + id;
            }
            System.out.println(sql);
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
