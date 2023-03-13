package com.muxin.system.designpattern;

/**
 * @Projectname: muxin
 * @Filename: ProxyPattern
 * @Author: yangxz
 * @Data:2022/12/29 16:29
 * @Description: 代理模式
 */
public class ProxyPattern {
    public static void main(String[] args) {
        SubjectProxy subject = new SubjectProxy();
        subject.doAction();
        subject.byebye();
    }



    // 共同接口 //
    public interface ISubject {
        void doAction();
        void byebye();
    }

    // 真实对象(委托类) //
    public static class RealSubject implements ISubject {
        @Override
        public void doAction() { System.out.println("Real Action Here!"); }
        @Override
        public void byebye() { System.out.println("Wave goodbye!"); }
    }

    // 代理对象(代理类) //
    public static class SubjectProxy implements ISubject {
        private ISubject subject;

        public SubjectProxy() {
            // RealSubject实例可根据环境变量、配置等创建不同类型的实例(多态)
            subject = new RealSubject(); // 此处仅简单地new实例
        }

        @Override
        public void doAction() {
            System.out.println(">> doWhatever start"); // 扩展进行额外的功能操作(如鉴权、计时、日志等)
            subject.doAction();
            System.out.println("doWhatever end <<");   // 扩展进行额外的功能操作(如鉴权、计时、日志等)
        }
        @Override
        public void byebye() {
            System.out.println("Say goodbye"); // 改变委托类行为(例如实现数据库连接池时避免close关闭连接)
        }
    }
}
