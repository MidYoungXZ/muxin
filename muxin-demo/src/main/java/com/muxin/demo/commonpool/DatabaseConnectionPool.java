package com.muxin.demo.commonpool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.sql.Connection;

public class DatabaseConnectionPool {

    private static GenericObjectPool<Connection> pool;

    static {
        // 配置连接池的参数
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(10); // 设置连接池的最大连接数
        config.setMaxIdle(5); // 设置连接池的最大空闲连接数
        config.setMinIdle(2); // 设置连接池的最小空闲连接数

        // 创建连接工厂
        DatabaseConnectionFactory factory = new DatabaseConnectionFactory(
                "jdbc:mysql://10.100.1.102:3306/sde_apolloconfigdb", "root", "root");

        // 初始化连接池
        pool = new GenericObjectPool<>(factory, config);
    }

    // 获取数据库连接
    public static Connection getConnection() throws Exception {
        return pool.borrowObject();
    }

    // 归还数据库连接到池
    public static void releaseConnection(Connection conn) {
        if (conn != null) {
            pool.returnObject(conn);
        }
    }

    // 关闭连接池（通常在应用程序关闭时调用）
    public static void close() {
        if (pool != null) {
            pool.close();
        }
    }
}
