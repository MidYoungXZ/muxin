package com.muxin.system.designpattern;

/**
 * @Projectname: muxin
 * @Filename: Singleton
 * @Author: yangxz
 * @Data:2022/12/29 10:03
 * @Description: 单例模式 懒汉式 饿汉式 双重检查机制
 */
public class SingletonPattern {


    /**
     * 饿汉式
     */
    //创建 SingleObject 的一个对象
    private static SingletonPattern instance = new SingletonPattern();

    //让构造函数为 private，这样该类就不会被实例化
    private SingletonPattern(){}

    //获取唯一可用的对象
    public static SingletonPattern getInstance(){
        return instance;
    }


//    /**
//     * 懒汉式
//     */
//    //成员变量私有但是不复制
//    private static Singleton instance;
//    //构造方法私有
//    private Singleton (){}
//
//    //对外提供获取实例方法
//    public static Singleton getInstance() {
//        if (instance == null) {
//            instance = new Singleton();
//        }
//        return instance;
//    }

//
//    /**
//     * 双重检查机制
//     */
//    private volatile static Singleton INSTANCE;
//
//    private Singleton() {
//    }
//
//    public static Singleton getInstance() {
//        if (INSTANCE == null) {
//            synchronized (Singleton.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new Singleton();
//                }
//            }
//        }
//        return INSTANCE;
//    }

//    外层判断null的作用：其实就是为了减少进入同步代码块的次数，提高效率。其实去了外层的判断其实是可以的，但是每次获取对象都需要进入同步代码块，实在是没有必要。
//    内层判断null的作用：防止多次创建对象。假设AB同时走到同步代码块，A先抢到锁，进入代码，创建了对象，释放锁，此时B进入代码块，如果没有判断null，那么就会直接再次创建对象，那么就不是单例的了，所以需要进行判断null，防止重复创建单例对象。
//    volatile关键字的作用：防止重排序。因为创建对象的过程不是原子，大概会分为三个步骤
//
//    第一步：分配内存空间给Singleton这个对象
//    第二步：初始化对象
//    第三步：将INSTANCE变量指向Singleton这个对象内存地址
//    假设没有使用volatile关键字发生了重排序，第二步和第三步执行过程被调换了，
//    也就是先将INSTANCE变量指向Singleton这个对象内存地址，再初始化对象。
//    这样在发生并发的情况下，另一个线程经过第一个if非空判断时，发现已经为不为空，
//    就直接返回了这个对象，但是此时这个对象还未初始化，内部的属性可能都是空值，
//    一旦被使用的话，就很有可能出现空指针这些问题。


}
