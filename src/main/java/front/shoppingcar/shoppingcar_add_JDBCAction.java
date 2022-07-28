package front.shoppingcar;

import front.ShangpinBean.ShangpinBean;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class shoppingcar_add_JDBCAction {
    public int PostInfobyID(ShangpinBean shangpin){
        PreparedStatement pSmt;
        try {
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
            String sql = "select max(id) as id from tb_gouwuche";
            pSmt = conn.prepareStatement(sql);
            ResultSet res = pSmt.executeQuery();
            if(res.next()){
                shangpin.setShoppingcar_id(res.getInt("id")+1);
            }else {
                shangpin.setShoppingcar_id(1);
            }
            sql = "select * from tb_user where name = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1,shangpin.getUser_name());
            res = pSmt.executeQuery();
            res.next();
            shangpin.setUser_id(res.getInt("id"));
            LocalDateTime time = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            shangpin.setShangpin_addtime(time.format(formatter));
            sql = "insert into tb_gouwuche (id,spid,uid,num,addtime) values(?,?,?,?,?)";
            pSmt = conn.prepareStatement(sql);
            pSmt.setInt(1,shangpin.getShoppingcar_id());
            pSmt.setInt(2,shangpin.getShangpin_id());
            pSmt.setInt(3,shangpin.getUser_id());
            pSmt.setInt(4,shangpin.getNum());
            pSmt.setString(5,shangpin.getShangpin_addtime());
            pSmt.executeUpdate();
            res.close();
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
