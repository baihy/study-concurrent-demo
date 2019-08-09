package com.baihy.singleton;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy
 * @description: 内部类方式
 * @author: huayang.bai
 * @date: 2019/08/09 9:27
 */
public class SingletonDemo3 {

    // 通过静态的内部类，实现了懒加载
    private static class Singleton {
        private static SingletonDemo3 singletonDemo3 = new SingletonDemo3();
    }

    /**
     * 私有化构造方法
     */
    private SingletonDemo3() {
        // 破除通过反射实例化对象
        if (null != Singleton.singletonDemo3) {
            throw new RuntimeException("单实例对象，禁止调用构造方法");
        }
    }

    // 返回单实例对象，线程安全的
    public static SingletonDemo3 newInstance() {
        return Singleton.singletonDemo3;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            // 开启100线程获取单实例对象。
            new Thread(() -> {
                System.out.println(SingletonDemo3.newInstance());
            }).start();
        }
    }

}
