package com.baihy.singleton;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy
 * @description: 饿汉式单例：特点是马上能够获取对象
 * @author: huayang.bai
 * @date: 2019/08/09 9:27
 */
public class SingletonDemo1 {

    /**
     * 饿汉式单例：
     * 保证实例化对象的机会只有一次。
     * 缺点是：线程安全
     * 不能懒加载。
     * 性能：效率不高。如果长时间不用的话，会浪费空间。
     */
    private static SingletonDemo1 singletonDemo1 = new SingletonDemo1();   // 类加载器，加载class文件时，就会实例化对象。
    // 而且变量是static类型的，放在了共享变量区中。

    /**
     * 私有化构造方法
     */
    private SingletonDemo1() {
        if (null != singletonDemo1) {
            // 破除通过反射实例化对象
            throw new RuntimeException("单实例对象，禁止调用构造方法");
        }
    }

    // 返回单实例对象
    public static SingletonDemo1 newInstance() {
        return singletonDemo1;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            // 开启100线程获取单实例对象。
            new Thread(() -> {
                System.out.println(SingletonDemo1.newInstance());
            }).start();
        }
    }
}
