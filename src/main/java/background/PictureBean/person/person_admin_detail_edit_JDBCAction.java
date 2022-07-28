package background.PictureBean.person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class person_admin_detail_edit_JDBCAction {
    public int PostInfobyedit(String category,int person_id,String var){
        PreparedStatement pSmt;
        try {
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
            String sql;
            if (Objects.equals(category, "name")){
                sql = "update tb_admin set name = ? where id = ?";
            }else {
                sql = "update tb_admin set pwd = ? where id = ?";
            }
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1,var);
            pSmt.setInt(2,person_id);
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
