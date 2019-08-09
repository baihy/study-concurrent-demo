package com.baihy.singleton;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy
 * @description: 懒汉式
 * @author: huayang.bai
 * @date: 2019/08/09 9:27
 */
public class SingletonDemo2 {
    /**
     * 懒汉式单例：保证实例化对象的机会只有一次。
     * 缺点是：不线程安全，
     * 实现了懒加载。
     */
    private static SingletonDemo2 singletonDemo2 = null;

    /**
     * 私有化构造方法
     */
    private SingletonDemo2() {
        if (null != singletonDemo2) {
            // 破除通过反射实例化对象
            throw new RuntimeException("单实例对象，禁止调用构造方法");
        }
    }

    // 返回单实例对象
    // 懒汉式存在线程不安全的问题。加上了synchronized之后，保证了线程安全。
    // 但是加了synchronized之后，在并发编程中，又退化到了串行执行，性能不好。
    // 这里方法上加上了synchronized关键字，性能不好，我们可以添加到语句块上，这就变成了双重检查实现单例模式
    public static synchronized SingletonDemo2 newInstance() {
        if (null == singletonDemo2) {
            singletonDemo2 = new SingletonDemo2();
        }
        return singletonDemo2;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            // 开启100线程获取单实例对象。
            new Thread(() -> {
                System.out.println(SingletonDemo2.newInstance());
            }).start();
        }
    }

}
