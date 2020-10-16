package com.redis.learn;

import java.sql.*;

public class StudentSql {
    private StudentSql(){

    }
    private static String Drivde="org.sqlite.JDBC";

    public static String selectSno(Integer sno) throws SQLException, ClassNotFoundException {
        Class.forName(Drivde);
        Connection conn= DriverManager.getConnection("jdbc:sqlite:D:\\files\\note\\test1.db");
        Statement state = conn.createStatement();
        String sql = String.format("select * from student where sno=%d", sno);
        ResultSet rs = state.executeQuery(sql);
        while (rs.next()) { //将查询到的数据打印出来
            String res = String.valueOf(sno) +"|"+ rs.getString("name")+"|"+rs.getString("age");
            //System.out.println(res);
            return res;
            //System.out.print("name = " + rs.getString("name") + " "); //列属性一
            //System.out.println("age = " + rs.getString("age")); //列属性二
        }

        rs.close();
        conn.close();
        return sql;
    }
}
