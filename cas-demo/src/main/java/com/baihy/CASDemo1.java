package com.baihy;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy
 * @description:
 * @author: huayang.bai
 * @date: 2019-08-11 17:12
 */
public class CASDemo1 {

    private static volatile int m = 0;
    // volatile关键字只保证了可见性和有序性，是没办法保证原子性的。


    // AtomicInteger是一个原子整型
    private static AtomicInteger ai = new AtomicInteger(0);


    public static void add1() {
        m++; // 而m++操作是一个非原子性操作。
    }


    public static void add2() {
        ai.incrementAndGet();// 这个方法的作用就是先获取，再++的原子操作


        //  ai.getAndIncrement();和ai.incrementAndGet();区别是：类似于++在前还是++在后的区别。
        //  原子变量：保证了原子性操作。
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                CASDemo1.add1();
            }).start();
        }
        System.out.println("add1方法最终m的值是：" + m);
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                CASDemo1.add2();
            }).start();
        }
        System.out.println("add2方法最终ai的值是：" + ai);
    }


}
