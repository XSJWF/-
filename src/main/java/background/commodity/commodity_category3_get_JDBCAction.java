package background.commodity;

import background.commodity_CategoryBean.CategoryBean;

import java.sql.*;
import java.util.ArrayList;

public class commodity_category3_get_JDBCAction {
    public ArrayList<CategoryBean> GetInfobyCategory(){
        ArrayList<CategoryBean> categorylist = new ArrayList<>();
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
            System.out.println("数据库加载成功！");
            String sql = "select * from tb_type3";
            pSmt = conn.prepareStatement(sql);
            res = pSmt.executeQuery();
            while (res.next()){
                CategoryBean category = new CategoryBean();
                category.setType3_id(res.getInt("id"));
                category.setType3_name(res.getString("typename"));
                category.setType2_id(res.getInt("typeid"));
                categorylist.add(category);
            }
            res.close();
            pSmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("数据库加载失败！");
            e.printStackTrace();
        }
        return categorylist;
    }
}
