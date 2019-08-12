package com.baihy;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy
 * @description:
 * @author: huayang.bai
 * @date: 2019-08-11 17:12
 */
public class CASDemo4 {
    // 定义一个拥有初始值和版本号的的原子变量
    private static AtomicStampedReference<Integer> asr = new AtomicStampedReference<>(100, 1);
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = asr.compareAndSet(100, 110, asr.getStamp(), asr.getStamp() + 1);
            boolean b1 = asr.compareAndSet(110, 100, asr.getStamp(), asr.getStamp() + 1);
            System.out.println("100改为110是否成功:" + b);
            System.out.println("110改为100是否成功:" + b1);
        }).start();
        System.out.println("***************************************************************************");
        new Thread(() -> {
            int stamp = asr.getStamp();
            System.out.println(stamp);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 注意：真实值不能随着版本号改变而改变。
            boolean b = asr.compareAndSet(100, 120, stamp, stamp + 1);
            // 在这个线程停留的3秒内有其他的线程对这个值，进行了修改，而拿着原来的版本号和值，进行修改的话，就不能使用了。
            // 四个参数的含义是：原子变量值的期望值，原子变量要修改的值，原子变量期望的版本，原子变量修改值之后的版本号
            System.out.println("100改为120是否成功:" + b);
        }).start();
        // 就是A----B------A办法的解决
    }
}
