package com.study.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtilsByDruid {

    private static DataSource ds;

    private static ThreadLocal<Connection> threadLocalConn = new ThreadLocal<>();


    //在静态代码块完成 ds初始化
    static {
        Properties properties = new Properties();
        try {
            properties.load(JDBCUtilsByDruid.class.getClassLoader().getResourceAsStream("druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        // 从当前ThreadLocal中获取连接
        Connection connection = threadLocalConn.get();
        if (connection == null) {
            //如果当前ThreadLocal中没有则从数据库连接池中取出一个连接放入ThreadLocal中
            try {
                connection = ds.getConnection();
                // 将连接设置为手动提交
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            threadLocalConn.set(connection);
        }
        return connection;
    }

    public static void commit(){
        Connection connection = threadLocalConn.get();
        if (connection != null){
            try {
                connection.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        // 释放连接池并避免内存泄露
        threadLocalConn.remove();
    }

    public static void rollback(){
        Connection connection = threadLocalConn.get();
        if(connection != null){
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        threadLocalConn.remove();
    }

    public static void close(ResultSet resultSet, Statement statement, Connection connection){
        try {
            if(resultSet != null){
                resultSet.close();
            }
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
