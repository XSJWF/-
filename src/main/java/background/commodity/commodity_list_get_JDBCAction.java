package background.commodity;

import background.CommodityBean.CommodityBean;

import java.sql.*;
import java.util.ArrayList;

public class commodity_list_get_JDBCAction {
    public ArrayList<CommodityBean> GetShangpinbyNull(){
        ArrayList<CommodityBean> shangpinlist = new ArrayList<>();
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
            String sql = "select * from tb_shangpin";
            pSmt = conn.prepareStatement(sql);
            res = pSmt.executeQuery();
            while(res.next()){
                CommodityBean shangpin = new CommodityBean();
                shangpin.setShangpin_id(res.getInt("id"));
                shangpin.setShangpin_mingcheng(res.getString("mingcheng"));
                shangpin.setShangpin_jianjie(res.getString("jianjie"));
                shangpin.setShangpin_addtime(res.getString("addtime"));
                shangpin.setShangpin_dengji(res.getString("dengji"));
                shangpin.setShangpin_xinghao(res.getString("xinghao"));
                shangpin.setShangpin_tupian(res.getString("tupian"));
                shangpin.setShangpin_shuliang(res.getInt("shuliang"));
                shangpin.setShangpin_cishu(res.getInt("cishu"));
                shangpin.setShangpin_tuijian(res.getInt("tuijian"));
                shangpin.setShangpin_typeid(res.getInt("typeid"));
                shangpin.setShangpin_type2id(res.getInt("type2id"));
                shangpin.setShangpin_type3id(res.getInt("type3id"));
                shangpin.setShangpin_huiyuanjia(res.getString("huiyuanjia"));
                shangpin.setShangpin_shichangjia(res.getString("shichangjia"));
                shangpin.setShangpin_pinpai(res.getString("pinpai"));
                shangpin.setShangpin_tejia(res.getInt("tejia"));
                shangpinlist.add(shangpin);
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
