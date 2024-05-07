package com.muxin.demo.commonpool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionFactory extends BasePooledObjectFactory<Connection> {

    private String connectionString;
    private String username;
    private String password;

    public DatabaseConnectionFactory(String connectionString, String username, String password) {
        this.connectionString = connectionString;
        this.username = username;
        this.password = password;
    }

    // 创建新的数据库连接
    @Override
    public Connection create() {
        try {
            return DriverManager.getConnection(connectionString, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("无法创建数据库连接", e);
        }
    }

    // 销毁数据库连接
    @Override
    public void destroyObject(PooledObject<Connection> p) throws Exception {
        p.getObject().close();
    }

    // 验证数据库连接是否有效
    @Override
    public boolean validateObject(PooledObject<Connection> p) {
        try {
            return p.getObject().isValid(1); // 设置一个非常短的超时，仅用于检查连接是否仍然可用
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public PooledObject<Connection> wrap(Connection connection) {
        return new DefaultPooledObject<>(connection);
    }

    // 激活对象（可选实现，这里我们什么也不做）
    @Override
    public void activateObject(PooledObject<Connection> p) throws Exception {
        // 可以在这里进行一些连接重新激活的操作，例如设置自动提交、隔离级别等
    }

    // 钝化对象（可选实现，这里我们什么也不做）
    @Override
    public void passivateObject(PooledObject<Connection> p) throws Exception {
        // 可以在对象返回到池之前执行一些清理或重置操作
    }
}

