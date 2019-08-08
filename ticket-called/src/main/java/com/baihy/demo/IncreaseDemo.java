package com.baihy.demo;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy.demo
 * @description:
 * @author: huayang.bai
 * @date: 2019/08/08 18:53
 */
public class IncreaseDemo {

    private static volatile int index = 0;


    public static void increase() {
        index++;
    }

    /***
     * 每次执行的结果还不一样
     * @param args
     */
    public static void main(String[] args) {
        // 表示的是，开启10个线程，没个线程对m的值做5次++操作
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    increase();
                }
            }).start();
        }
        // 注意：这个m值的输出是主线程输出的，并不是所有线程执行完成之后输出的结果。
        System.out.println("index最后的值是：" + index);
        // 推荐使用CountDownLatch进行线程执行的等待
    }

}
