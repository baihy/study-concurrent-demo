package com.baihy.utils;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy.utils
 * @description:
 * @author: huayang.bai
 * @date: 2019-08-14 20:20
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        //步骤是：
        //1.实例化Semaphore对象。
        //2.请求资源
        // 3.使用资源---处理业务逻辑
        // 4.释放资源
        Semaphore semaphore = new Semaphore(3); // 参数的含义是：设置资源的数量
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    semaphore.acquire(); // 请求资源
                    System.out.println(Thread.currentThread().getName() + "能够获取资源，可以使用资源");
                    // 使用资源----处理业务逻辑
                    System.out.println(Thread.currentThread().getName() + "处理逻辑开始");
                    int time = 10;// new Random().nextInt(10);
                    System.out.println("处理业务逻辑需要" + time + "秒");
                    TimeUnit.SECONDS.sleep(time);
                    System.out.println(Thread.currentThread().getName() + "处理逻辑完成");
                    // 释放资源
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + "释放资源完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }


    }

}
