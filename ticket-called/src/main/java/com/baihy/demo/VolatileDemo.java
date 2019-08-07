package com.baihy.demo;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy.demo
 * @description: Volatile关键字
 * @author: huayang.bai
 * @date: 2019/08/07 17:25
 */
public class VolatileDemo {

    static volatile int index = 1;
    final static int MAX = 3;

    public static void main(String[] args) {
        new Thread(() -> {
            while (index <= MAX) {
                System.out.println(Thread.currentThread().getName() + "：" + index);
            }
        }, "read").start();
        new Thread(() -> {
            while (index <= MAX) {
                System.out.println(Thread.currentThread().getName() + "：" + (++index));
            }
        }, "write").start();
    }
}
