package front.Orders;

import front.OrderBean.OrderBean;

import java.sql.*;
import java.util.ArrayList;

public class Orders_JDBCAction {
    public ArrayList<OrderBean> GetOrderbyName(String xiadanren){
        PreparedStatement pSmt;
        ResultSet res;
        ArrayList<OrderBean> OrderList = new ArrayList<>();
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("数据库驱动加载成功！");
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动加载失败！");
            e.printStackTrace();
        }
        String url = "jdbc:sqlserver://localhost:1433; DatabaseName = tb";
        try{
            Connection conn = DriverManager.getConnection(url,"DYL","FOREVERDYL121");
            System.out.println("数据库连接成功！");
            String sql = "select * from tb_dingdan where xiadanren = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1,xiadanren);
            res = pSmt.executeQuery();
            while(res.next()) {
                OrderBean order = new OrderBean();
                order.setDingdanhao(res.getString("dingdanhao"));
                order.setShouhuoren(res.getString("shouhuoren"));
                order.setSex(res.getString("sex"));
                order.setDizhi(res.getString("dizhi"));
                order.setYoubian(res.getString("youbian"));
                order.setTel(res.getString("tel"));
                order.setShsj(res.getString("shsj"));
                order.setZffs(res.getString("zffs"));
                order.setLeaveword(res.getString("leaveword"));
                order.setTime(res.getString("time"));
                order.setXiadanren(res.getString("xiadanren"));
                order.setZt(res.getString("zt"));
                order.setTotal(res.getString("total"));
                OrderList.add(order);
            }
            res.close();
            pSmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
        return OrderList;
    }
}
