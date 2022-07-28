package background.PictureBean.person;

import background.PersonBean.PersonBean;

import java.sql.*;

public class person_admin_detail_JDBCAction {
    public PersonBean GetInfobyID(PersonBean person){
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
            String sql = "select * from tb_admin where name = ?";
            pSmt = conn.prepareStatement(sql);
            pSmt.setString(1,person.getPerson_name());
            res = pSmt.executeQuery();
            res.next();
            person.setPerson_id(res.getInt("id"));
            person.setPerson_pwd(res.getString("pwd"));
            res.close();
            pSmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
        return person;
    }
}
