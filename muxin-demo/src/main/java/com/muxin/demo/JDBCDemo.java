package com.muxin.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//快速入门
public class JDBCDemo {
    public static void main(String[] args) throws Exception {
        //1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/databaseName";
        String username = "yourUsername";
        String password = "yourPassword";

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // 使用占位符设置参数
            preparedStatement.setString(1, "userInputUsername");
            preparedStatement.setString(2, "userInputPassword");

            // 执行查询
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();

            // 处理结果
            while (resultSet.next()) {
                // 获取数据
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}