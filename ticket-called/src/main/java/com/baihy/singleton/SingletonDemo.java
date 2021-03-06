package com.baihy.singleton;

import java.io.Serializable;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy
 * @description: 双重检查，实现单实例对象
 * @author: huayang.bai
 * @date: 2019/08/09 9:27
 */
public class SingletonDemo implements Serializable {

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
            // 因为这里加了synchronized关键字，就相当于是退化成了串行执行。
            synchronized (SingletonDemo.class) { // 如果把锁直接放到外边，把第一次判断，去掉的话，这样每次调用方法获取对象时，都会加锁，这样也会影响性能。
                // 注意：synchronized修饰的变量不能为null
                // 第二次检查是为了保证多个线程同时通过了第一次检查
                if (null == singletonDemo) {
                    singletonDemo = new SingletonDemo();
                }
            }
        }
        return singletonDemo;
    }


    // 防止通过序列化和反序列化来破解单例
    private Object readResolve() {
        return singletonDemo;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            // 开启100线程获取单实例对象。
            new Thread(() -> {
                System.out.println(SingletonDemo.newInstance());
            }).start();
        }
    }

}
