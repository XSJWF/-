package front.shoppingcar;

import front.ShangpinBean.ShangpinBean;

import java.sql.*;
import java.util.ArrayList;

public class shoppingcar_get_JDBCAction {
    public ArrayList<ShangpinBean> GetInfobyName(String username){
        PreparedStatement pSmt;
        ResultSet res;
        ArrayList<ShangpinBean> shangpinlist = new ArrayList<>();
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
            String sql = "select * from tb_user where name = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1,username);
            res = pSmt.executeQuery();
            res.next();
            int user_id = res.getInt("id");
            sql = "select * from tb_gouwuche where uid = ?";
            pSmt =conn.prepareStatement(sql);
            pSmt.setInt(1,user_id);
            res = pSmt.executeQuery();
            while(res.next()){
                ShangpinBean shangpin = new ShangpinBean();
                shangpin.setShoppingcar_id(res.getInt("id"));
                shangpin.setShangpin_id(res.getInt("spid"));
                shangpin.setNum(res.getInt("num"));
                sql = "select * from tb_shangpin where id = ?";
                pSmt = conn.prepareStatement(sql);
                pSmt.setInt(1,shangpin.getShangpin_id());
                ResultSet res1 = pSmt.executeQuery();
                res1.next();
                shangpin.setShangpin_jianjie(res1.getString("jianjie"));
                shangpin.setShangpin_price(res1.getString("huiyuanjia"));
                shangpinlist.add(shangpin);
                res1.close();
            }
            res.close();
            pSmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
        return shangpinlist;
    }
}
