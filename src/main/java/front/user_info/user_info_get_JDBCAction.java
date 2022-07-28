package front.user_info;

import front.UserBean.UserBean;

import java.sql.*;
import java.util.Objects;

public class user_info_get_JDBCAction {
    public UserBean GetUserbyName(UserBean user){
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
        try{
            Connection conn = DriverManager.getConnection(url, "DYL", "FOREVERDYL121");
            System.out.println("数据库连接成功！");
            String sql = "select * from tb_user where name = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1,user.getUsername());
            res = pSmt.executeQuery();
            res.next();
            user.setTelephone(res.getString("tel"));
            user.setEmail(res.getString("email"));
            if (Objects.equals(res.getString("qq"), "0")){
                user.setQq("");
            }else {
                user.setQq(res.getString("qq"));
            }
            if (Objects.equals(res.getString("dizhi"), "0")){
                user.setDizhi("");
            }else{
                user.setDizhi(res.getString("dizhi"));
            }
            if (Objects.equals(res.getString("youbian"), "0")){
                user.setYoubian("");
            }else{
                user.setYoubian(res.getString("youbian"));
            }
            if (Objects.equals(res.getString("truename"), "0")){
                user.setDizhi("");
            }else{
                user.setTruename(res.getString("truename"));
            }
            res.close();
            pSmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
        return user;
    }
}
