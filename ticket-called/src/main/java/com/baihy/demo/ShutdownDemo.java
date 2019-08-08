package com.baihy.demo;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy.demo
 * @description:
 * @author: huayang.bai
 * @date: 2019-08-08 22:17
 */
public class ShutdownDemo {

    public static volatile boolean flag = true;

    public static void main(String[] args) {
        new Thread(() -> {
            while (flag) {
                System.out.println("*************");
            }
        }).start();
        // 在一个线程中，控制另一个线程的执行。
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if (i == 10) {
                    flag = false;
                }
            }
        }).start();

    }

}
