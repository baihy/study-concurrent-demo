package com.baihy.demo;

import java.util.concurrent.TimeUnit;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy.demo
 * @description: Volatile关键字
 * @author: huayang.bai
 * @date: 2019/08/07 17:25
 */
public class VolatileDemo {

    /**
     * 两个线程：当写线程把index的值，改变之后，读线程就能里面感知到。
     * 注意：有无volatile关键字的执行结果的区别
     */
    static volatile int index = 0;
    final static int MAX = 5;

    public static void main(String[] args) {
        new Thread(() -> {
            int index_temp = index;
            while (index_temp <= MAX) {
                if (index_temp != index) {
                    System.out.println(Thread.currentThread().getName() + "：" + index);
                    index = index_temp;
                }
            }
        }, "read").start();
        new Thread(() -> {
            int index_temp = index;
            while (index <= MAX) {
                System.out.println(Thread.currentThread().getName() + "：" + (++index_temp));
                index = index_temp;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "write").start();
    }
}
