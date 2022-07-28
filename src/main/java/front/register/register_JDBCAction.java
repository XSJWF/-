package front.register;

import front.UserBean.UserBean;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class register_JDBCAction {
    public int GetUserbyName(UserBean user) {
        PreparedStatement pSmt;
        ResultSet res ;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("数据库驱动加载成功！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName = tb";
        try {
            Connection conn = DriverManager.getConnection(url, "DYL", "FOREVERDYL121");
            System.out.println("数据库连接成功！");
            String sql = "select * from tb_user where name = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1,user.getUsername());
            res = pSmt.executeQuery();
            if(res.next()){
                return 0;
            }else {
                sql = "select max(id) as id from tb_user";
                pSmt = conn.prepareStatement(sql);
                res = pSmt.executeQuery();
                if (res.next()){
                    user.setId(res.getInt("id")+1);
                }else {
                    user.setId(1);
                }
                LocalDateTime time = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                try {
                    InetAddress ip = Inet4Address.getLocalHost();
                sql = "insert into tb_user (id,name,pwd,dongjie,email,tel,qq,ip,tishi,huida,dizhi,youbian,regtime,lastlogintime,logincishu,truename,pwd1) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                pSmt = conn.prepareStatement(sql);
                pSmt.setInt(1,user.getId());
                pSmt.setString(2,user.getUsername());
                pSmt.setString(3,user.getPassword());
                pSmt.setInt(4,1);
                pSmt.setString(5,user.getEmail());
                pSmt.setString(6,user.getTelephone());
                pSmt.setString(7,"0");
                pSmt.setString(8,ip.getHostAddress());
                pSmt.setString(9,user.getQuestion());
                pSmt.setString(10,user.getAnswer());
                pSmt.setString(11,"0");
                pSmt.setString(12,"0");
                pSmt.setString(13,time.format(formatter));
                pSmt.setString(14,"0");
                pSmt.setInt(15,0);
                pSmt.setString(16,"0");
                pSmt.setString(17,user.getC_password());
                pSmt.executeUpdate();
                } catch (UnknownHostException | SQLException e) {
                    e.printStackTrace();
                }
            }
            res.close();
            pSmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
