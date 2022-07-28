package front.OrderBean.form_orders;

import front.OrderBean.OrderBean;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class form_orders_post_JDBCAction {
    public int PostDingdanbyInfo(OrderBean order){
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
            String sql = "select max(id) as id from tb_dingdan";
            pSmt = conn.prepareStatement(sql);
            res = pSmt.executeQuery();
            int id;
            if(res.next()){
                id = res.getInt("id") + 1;
            }else {
                id = 1;
            }
            String dingdanhao;
            LocalDateTime timeNow = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dingdanhao = timeNow.format(formatter);
            String time = timeNow.format(formatter1);
            sql = "insert into tb_dingdan (id,dingdanhao,shouhuoren,sex,dizhi,youbian,tel,shsj,zffs,leaveword,time,xiadanren,zt,total) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pSmt = conn.prepareStatement(sql);
            pSmt.setInt(1,id);
            pSmt.setString(2,dingdanhao);
            pSmt.setString(3,order.getShouhuoren());
            pSmt.setString(4,order.getSex());
            pSmt.setString(5,order.getDizhi());
            pSmt.setString(6,order.getYoubian());
            pSmt.setString(7,order.getTel());
            pSmt.setString(8,"0");
            pSmt.setString(9,"在线支付");
            pSmt.setString(10,order.getLeaveword());
            pSmt.setString(11,time);
            pSmt.setString(12,order.getXiadanren());
            pSmt.setString(13,"已支付");
            pSmt.setString(14,order.getTotal());
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
