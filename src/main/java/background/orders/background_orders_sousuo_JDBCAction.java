package background.orders;

import background.OrderBean.OrderBean;

import java.sql.*;

public class background_orders_sousuo_JDBCAction {
    public OrderBean GetOrderbyID(String dingdanhao){
        OrderBean dingdan = new OrderBean();
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
            String sql = "select * from tb_dingdan where dingdanhao = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1,dingdanhao);
            res = pSmt.executeQuery();
            res.next();
            dingdan.setOrder_id(res.getInt("id"));
            dingdan.setOrder_dingdanhao(res.getString("dingdanhao"));
            dingdan.setOrder_shouhuoren(res.getString("shouhuoren"));
            dingdan.setOrder_sex(res.getString("sex"));
            dingdan.setOrder_dizhi(res.getString("dizhi"));
            dingdan.setOrder_youbian(res.getString("youbian"));
            dingdan.setOrder_tel(res.getString("tel"));
            dingdan.setOrder_shsj(res.getString("shsj"));
            dingdan.setOrder_zffs(res.getString("zffs"));
            dingdan.setOrder_leaveword(res.getString("leaveword"));
            dingdan.setOrder_time(res.getString("time"));
            dingdan.setOrder_xiadanren(res.getString("xiadanren"));
            dingdan.setOrder_zt(res.getString("zt"));
            dingdan.setOrder_total(res.getString("total"));
            res.close();
            pSmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
        return dingdan;
    }
}
