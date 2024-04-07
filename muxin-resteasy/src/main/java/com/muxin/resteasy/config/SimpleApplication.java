package com.muxin.resteasy.config;




import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


/**
 * @projectname: muxin
 * @filename: SimpleApplication
 * @author: yangxz
 * @data:2024/3/6 14:19
 * @description:
 */


public class SimpleApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        // 添加资源类
        classes.add(SimpleResource.class);
        return classes;
    }
}
