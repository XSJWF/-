package front.Orders_detail;

import front.OrderBean.OrderBean;

import java.sql.*;

public class Orders_detail_JDBCAction {
    public OrderBean GetOrderbydingdanhao(String dingdanhao){
        OrderBean order = new OrderBean();
        order.setDingdanhao(dingdanhao);
        PreparedStatement pSmt;
        ResultSet res;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("数据库驱动加载成功！");
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动加载失败！");
            e.printStackTrace();
        }
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName = tb";
        try{
            Connection conn = DriverManager.getConnection(url,"DYL","FOREVERDYL121");
            System.out.println("数据库连接成功！");
            String sql = "select * from tb_dingdan where dingdanhao = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1,dingdanhao);
            res = pSmt.executeQuery();
            res.next();
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
            res.close();
            pSmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
        return order;
    }
}
