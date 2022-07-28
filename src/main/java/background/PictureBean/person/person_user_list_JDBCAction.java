package background.PictureBean.person;

import background.PersonBean.PersonBean;

import java.sql.*;
import java.util.ArrayList;

public class person_user_list_JDBCAction {
    public ArrayList<PersonBean> GetInfobyPerson(){
        ArrayList<PersonBean> personlist = new ArrayList<>();
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
            String sql = "select * from tb_user";
            pSmt = conn.prepareStatement(sql);
            res = pSmt.executeQuery();
            while(res.next()){
                PersonBean person = new PersonBean();
                person.setPerson_id(res.getInt("id"));
                person.setPerson_name(res.getString("name"));
                person.setPerson_pwd(res.getString("pwd"));
                person.setPerson_dongjie(res.getInt("dongjie"));
                person.setPerson_email(res.getString("email"));
                person.setPerson_tel(res.getString("tel"));
                person.setPerson_qq(res.getString("qq"));
                person.setPerson_ip(res.getString("ip"));
                person.setPerson_tishi(res.getString("tishi"));
                person.setPerson_huida(res.getString("huida"));
                person.setPerson_dizhi(res.getString("dizhi"));
                person.setPerson_youbian(res.getString("youbian"));
                person.setPerson_regtime(res.getString("regtime"));
                person.setPerson_lastlogintime(res.getString("lastlogintime"));
                person.setPerson_logincishu(res.getInt("logincishu"));
                person.setPerson_truename(res.getString("truename"));
                person.setPerson_pwd1(res.getString("pwd1"));
                personlist.add(person);
            }
            res.close();
            pSmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
        return personlist;
    }
}
