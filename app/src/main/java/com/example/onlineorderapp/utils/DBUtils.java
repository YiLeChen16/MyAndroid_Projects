package com.example.onlineorderapp.utils;

import android.util.Log;

import com.example.onlineorderapp.bean.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//数据库工具类:使用JDBC连接数据库
/*用于MYSQL数据库的连接、断开、数据库数据的增删改查等一系列操作
* */
public class DBUtils {
    // MySql驱动
    private static final String driver = "com.mysql.jdbc.Driver";
    //数据库名
    public static final String DB_name = "onlineorder_db";
    //表名
    private static final String Table_name = "user_data";
    //用户名
    private static final String user = "root";
    //密码
    private static final String password = "xxxxxx";
    //数据库URL地址
    private static final String url = "jdbc:mysql://192.168.208.239:3306/" + DB_name;



    //获取数据库连接
    public static Connection getConn(){
        Connection connection = null;

        try {
            //动态加载MYSQL驱动类
            Class.forName(driver);
            //连接数据库
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //向数据库中插入数据
    public static boolean insert(String phoneNumber,String password) throws SQLException {
        //获取数据库连接
        Connection conn = getConn();
        if(conn != null){
            Log.d("tag","数据库连接成功！");
        }
        else {
            Log.d("tag","数据库连接失败！");
            return  false;
        }
        //定义sql语句,不需要加分号，因为使用了预编译
        String sql = "insert into " + Table_name + " (phone_number, password) values (" + phoneNumber + ", '" + password + "')";
        //获取执行SQL的对象
        Statement statement = conn.createStatement();
        //执行SQL语句
        int count = statement.executeUpdate(sql);
        //释放资源
        statement.close();
        conn.close();
        //判断sql语句执行结果
        if(count > 0){
            Log.d("tag","数据插入成功！");
            return true;
        }
        else {
            Log.d("tag","数据插入失败！");
            return false;
        }
    }

    //查询全部数据
    /*
    * 将查询的数据封装为一个User实体类，再存储到集合中
    * */
    public static ArrayList<User> query() throws SQLException {
        //创建集合用于存储查询结果
        ArrayList<User> userList = new ArrayList<>();
        //获取数据库连接
        Connection conn = getConn();
        if(conn != null){
            Log.d("tag","数据库连接成功！");
        }
        else {
            Log.d("tag","数据库连接失败！");
            return null;
        }
        //定义sql语句
        String sql = "select * from " + Table_name;
        //获取执行SQL语句的对象
        Statement statement = conn.createStatement();
        //执行SQL语句,并获取结果集
        ResultSet resultSet = statement.executeQuery(sql);
        //处理查询结果
        while (resultSet.next()){
            String phoneNumber = resultSet.getString("phone_number");
            String password = resultSet.getString("password");
            User user = new User(phoneNumber,password);
            //将用户对象存储到集合中
            userList.add(user);
        }
        //释放资源
        statement.close();
        conn.close();
        return userList;
    }


}
