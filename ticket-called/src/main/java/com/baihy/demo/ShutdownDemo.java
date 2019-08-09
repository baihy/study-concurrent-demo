package com.baihy.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
            System.out.println("开始执行时间：" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            int index = 0;
            while (flag) {
                System.out.print(Thread.currentThread().getName() + "：*************");
                if (index++ % 8 == 0) {
                    System.out.println("");
                }
            }
            System.out.println("结束执行时间：" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }, "被控制线程").start();
        new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 运行2分钟之后，停止被控制线程
            flag = false;
        }, "控制线程").start();
    }

}
