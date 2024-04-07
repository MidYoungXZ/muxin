package com.muxin.resteasy;


import com.muxin.resteasy.config.SimpleApplication;
import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;
/**
 * @projectname: muxin
 * @filename: SimpleRestServer
 * @author: yangxz
 * @data:2024/3/6 14:19
 * @description:
 */
public class SimpleRestServer {

    public static void main(String[] args) {
        // 创建NettyJaxrsServer
        NettyJaxrsServer netty = new NettyJaxrsServer();

        // 设置端口号
        netty.setPort(8080);

        // 设置根路径
        netty.setRootResourcePath("/");

        // 创建ResteasyDeployment，并添加资源类
        ResteasyDeployment deployment = netty.getDeployment();
        deployment.setApplicationClass(SimpleApplication.class.getName());

        // 启动服务器
        netty.start();
    }
}
