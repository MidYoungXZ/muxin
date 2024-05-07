package com.muxin.demo.commonpool;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * @projectname: muxin
 * @filename: Demo1
 * @author: yangxz
 * @data:2024/5/7 上午11:34
 * @description:
 */
public class Demo1 {


    public static void main(String[] args) {
        // 从连接池中获取连接
        try (Connection conn = DatabaseConnectionPool.getConnection()) {
            // 使用连接执行数据库操作
            ResultSet resultSet = conn.prepareStatement("select * from app").executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1)+" "+ resultSet.getString(2));
            }
            // 连接会在try-with-resources块结束时自动归还到池中
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }

        // 注意：在应用程序结束时，应该调用DatabaseConnectionPool.close()来关闭连接池。
    }
}
