package com.muxin.system.designpattern;

/**
 * @Projectname: muxin
 * @Filename: AdapterPattern
 * @Author: yangxz
 * @Data:2022/12/29 16:54
 * @Description: 适配器模式
 */
public class AdapterPattern {





    /**
     * Mybatis Log
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("类适配器模式测试：");
        Target target = new ClassAdapter();
        target.request();
    }



    /**
     * 这是客户所期待的接口，目标可以是具体的或抽象类，也可以是接口
     */
    interface Target {
        void request();
    }

    /**
     * 需要适配的类，被访问和适配的现存组件库中的组件接口
     */
    static  class Adaptee {
        public void specificRequest(){
            System.out.println("适配者中的业务代码被调用！");
        }
    }

    /**
     * 类适配器类
     */
    static class ClassAdapter extends Adaptee implements Target{
        @Override
        public void request() {
            specificRequest();
        }
    }

    /**
     * 对象适配器模式
     */
    static class ObjectAdapter implements Target {
        private Adaptee adaptee;

        public ObjectAdapter(Adaptee adaptee) {
            this.adaptee = adaptee;
        }

        @Override
        public void request() {
            adaptee.specificRequest();
        }
    }

}
