package com.redis.learn;

import redis.clients.jedis.Jedis;

import java.sql.SQLException;
import java.sql.SQLOutput;

public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        App app = new App();
        app.testSet("sno1", "110");
        app.testGet("sno1");
        app.testDel("sno1");


        final String val1 = app.testGet("120");
        if (null != val1 && val1.length()!=0) {
            String[] splitArray = val1.split("\\|");
            String Sno = "sno: " + splitArray[0];
            String Name = "name: " + splitArray[1];
            String Age = "age: " + splitArray[2];
            System.out.println(Sno);
            System.out.println(Name);
            System.out.println(Age);
        } else {
            String sql_res = StudentSql.selectSno(120);
            //System.out.println(sql_res);
            app.testSet("120", sql_res);
            app.getSno("120");
        }
        //app.testGet("220");

    }
    //保存数据
    public void testSet(String key, String val){
        final Jedis resource = RedisFactory.getInstance().getResource();
        try{
            resource.set(key, val);
            resource.expire(key,1800);
        }finally {
            resource.close();
        }
    }

    public String testGet(String key){
        final Jedis resource = RedisFactory.getInstance().getResource();
        try{
            final String val1 = resource.get(key);
            return val1;
            //System.out.println(val1);
        }finally {
            resource.close();
        }
    }
    //删除数据
    public void testDel(String key){
        final Jedis resource = RedisFactory.getInstance().getResource();
        try{
            resource.del(key);
        }finally {
            resource.close();
        }
    }
    public void getSno(String key){
        final Jedis resource = RedisFactory.getInstance().getResource();
        try{
            final String val1 = resource.get(key);
            String[] splitArray = val1.split("\\|");
            String Sno = "sno: " + splitArray[0];
            String Name = "name: " + splitArray[1];
            String Age = "age: " + splitArray[2];
            System.out.println(Sno);
            System.out.println(Name);
            System.out.println(Age);
        }finally {
            resource.close();
        }
    }
}
