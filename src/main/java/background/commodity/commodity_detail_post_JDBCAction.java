package background.commodity;

import background.CommodityBean.CommodityBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class commodity_detail_post_JDBCAction {
    public int PostInfobyID(CommodityBean commodity){
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
            System.out.println("数据库连接成功！");
            String sql = "update tb_shangpin set mingcheng = ?,jianjie = ?,addtime = ?,dengji = ?,xinghao = ?,tupian = ?,shuliang = ?,cishu = ?,tuijian = ?,typeid = ?,type2id = ?,type3id = ?,huiyuanjia = ?,shichangjia = ?,pinpai = ?,tejia = ? where id = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1,commodity.getShangpin_mingcheng());
            pSmt.setString(2,commodity.getShangpin_jianjie());
            pSmt.setString(3,commodity.getShangpin_addtime());
            pSmt.setString(4,commodity.getShangpin_dengji());
            pSmt.setString(5,commodity.getShangpin_xinghao());
            pSmt.setString(6,commodity.getShangpin_tupian());
            pSmt.setInt(7,commodity.getShangpin_shuliang());
            pSmt.setInt(8,commodity.getShangpin_cishu());
            pSmt.setInt(9,commodity.getShangpin_tuijian());
            pSmt.setInt(10,commodity.getShangpin_typeid());
            pSmt.setInt(11,commodity.getShangpin_type2id());
            pSmt.setInt(12,commodity.getShangpin_type3id());
            pSmt.setString(13,commodity.getShangpin_huiyuanjia());
            pSmt.setString(14,commodity.getShangpin_shichangjia());
            pSmt.setString(15,commodity.getShangpin_pinpai());
            pSmt.setInt(16,commodity.getShangpin_tejia());
            pSmt.setInt(17,commodity.getShangpin_id());
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
