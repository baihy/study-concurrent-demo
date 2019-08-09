package com.baihy.singleton;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy
 * @description: 双重检查，实现单实例对象
 * @author: huayang.bai
 * @date: 2019/08/09 9:27
 */
public class SingletonDemo {

    //////这个成员变需要加上volatile的作用就是多线程环境中，让其他线程感知singletonDemo已经被初始化///////////
    private static volatile SingletonDemo singletonDemo = null;
    // 不加volatile关键字的时候，会出现指令重排的问题。
    private SingletonDemo() {
        if (null != singletonDemo) {
            // 破除通过反射实例化对象
            throw new RuntimeException("单实例对象，禁止调用构造方法");
        }
    }

    // 返回单实例对象
    public static SingletonDemo newInstance() {
        // 双重检查确保对象是单例的
        if (null == singletonDemo) {
            synchronized (SingletonDemo.class) {
                // 注意：synchronized修饰的变量不能为null
                // 第二次检查是为了保证多个线程同时通过了第一次检查
                if (null == singletonDemo) {
                    singletonDemo = new SingletonDemo();
                }
            }
        }
        return singletonDemo;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            // 开启100线程获取单实例对象。
            new Thread(() -> {
                System.out.println(SingletonDemo.newInstance());
            }).start();
        }
    }

}
