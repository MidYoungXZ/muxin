package com.muxin.demo.designpattern;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Projectname: muxin
 * @Filename: DynamicProxyPattern
 * @Author: yangxz
 * @Data:2023/2/9 14:32
 * @Description:
 */
@Slf4j
public class DynamicProxyPattern {

    public static void main(String[] args) {
        log.info("开始");
        ProxyHandler handler = new ProxyHandler();
        Subject subject = (Subject) handler.newProxyInstance(new ConcreteSubject());
        subject.request();
        log.info("结束");
    }

     interface Subject {
        /**
         * 接口方法
         */
        public void request();
    }

    static class ConcreteSubject implements Subject {
        /**
         * 具体的业务逻辑实现
         */
        @Override
        public void request() {
            //业务处理逻辑
            System.out.println("具体的业务逻辑实现");
        }
    }

    static class ProxyHandler implements InvocationHandler {
        /**
         * 目标对象
         */
        private Object target;

        /**
         * 绑定关系，也就是关联到哪个接口（与具体的实现类绑定）的哪些方法将被调用时，执行invoke方法。
         *
         * @param target 绑定具体的代理实例
         * @return 动态代理类实例
         */
        public Object newProxyInstance(Object target) {
            this.target = target;
        /*
        该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例。
        第一个参数指定产生代理对象的类加载器，需要将其指定为和目标对象同一个类加载器。
        第二个参数要实现和目标对象一样的接口，所以只需要拿到目标对象的实现接口。
        第三个参数表明这些被拦截的方法在被拦截时需要执行哪个InvocationHandler的invoke方法
        根据传入的目标返回一个代理对象
        */
            Object result = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(), this);
            return result;
        }

        /**
         * 关联的这个实现类的方法被调用时将被执行。InvocationHandler接口的方法。
         *
         * @param proxy  代理
         * @param method 原对象被调用的方法
         * @param args   方法的参数
         * @return
         * @throws Throwable
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("调用方法前");
            Object ret = null;
            try {
                //调用目标方法
                ret = method.invoke(target, args);
            } catch (Exception e) {
                log.error("调用{}.{}发生异常", target.getClass().getName(), method.getName(), e);
                throw e;
            }
            System.out.println("调用方法后");
            return ret;
        }
    }


}
