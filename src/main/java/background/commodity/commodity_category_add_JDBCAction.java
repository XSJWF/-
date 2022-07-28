package background.commodity;

import java.sql.*;
import java.util.Objects;

public class commodity_category_add_JDBCAction {
    public int InsertInfobyadd(String category,String typename,String typeid){
        PreparedStatement pSmt;
        ResultSet res;
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
            int id;
            if(Objects.equals(category, "1")){
                sql = "select max(id) as id from tb_type";
                pSmt = conn.prepareStatement(sql);
                res = pSmt.executeQuery();
                if (res.next()){
                    id = res.getInt("id") + 1;
                }else {
                    id = 1;
                }
                sql = "insert into tb_type (id,typename) values(?,?)";
                pSmt = conn.prepareStatement(sql);
                pSmt.setInt(1,id);
                pSmt.setString(2,typename);
                pSmt.executeUpdate();
            }else if(Objects.equals(category, "2")){
                sql = "select max(id) as id from tb_type2";
                pSmt = conn.prepareStatement(sql);
                res = pSmt.executeQuery();
                if (res.next()){
                    id = res.getInt("id") + 1;
                }else {
                    id = 1;
                }
                sql = "insert into tb_type2 (id,typename,typeid) values(?,?,?)";
                pSmt = conn.prepareStatement(sql);
                pSmt.setInt(1,id);
                pSmt.setString(2,typename);
                pSmt.setInt(3, Integer.parseInt(typeid));
                pSmt.executeUpdate();
            }else {
                sql = "select max(id) as id from tb_type3";
                pSmt = conn.prepareStatement(sql);
                res = pSmt.executeQuery();
                if (res.next()){
                    id = res.getInt("id") + 1;
                }else {
                    id = 1;
                }
                sql = "insert into tb_type3 (id,typename,typeid) values(?,?,?)";
                pSmt = conn.prepareStatement(sql);
                pSmt.setInt(1,id);
                pSmt.setString(2,typename);
                pSmt.setInt(3, Integer.parseInt(typeid));
                pSmt.executeUpdate();
            }
            res.close();
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
