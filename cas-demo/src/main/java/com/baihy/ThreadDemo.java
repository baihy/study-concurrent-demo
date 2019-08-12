package com.baihy;

import java.util.concurrent.TimeUnit;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy
 * @description:
 * @author: huayang.bai
 * @date: 2019-08-11 17:42
 */
public class ThreadDemo {


    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("***************");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完毕");
        });
        thread.start();
        try {
            thread.join(); // 方法的含义就是把Thread线程加入到main线程中，等到thread线程执行完毕之后，才会继续执行main线程
            // join()相当于是线程的执行是有序的。就是线程被暂停，有序的执行其他的线程。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main线程执行");

    }
}