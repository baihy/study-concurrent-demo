package com.baihy;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy
 * @description:
 * @author: huayang.bai
 * @date: 2019-08-11 17:12
 */
public class CASDemo2 {
    // 定义一个拥有初始值的原子变量
    private static AtomicInteger ai = new AtomicInteger(100);


    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            // 第一参数是期望值，期望值和原来的旧值是相等，才能 完成更新
            boolean b = ai.compareAndSet(100, 110);
            System.out.println("100改为110是否成功:" + b);
        }).start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            boolean b = ai.compareAndSet(110, 100);
            System.out.println("110改为100是否成功:" + b);
        }).start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            // 这个线程是不知道上面两个线程对变量值的修改的。这就是原子变量的缺点。那么如何解决呢？AtomicStampedReference
            boolean b = ai.compareAndSet(100, 120);
            System.out.println("100改为120是否成功:" + b);
        }).start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            // 上一步执行完成之后，ai的值应该变为了120，那么如果期望值和初始值不一样的话，会有修改成功么？
            boolean b = ai.compareAndSet(100, 110);
            System.out.println("期望值和原子变量的初始值不一样是否能都改成功:" + b);
            System.out.println("修改之后的值是:" + ai.get());
        }).start();
        // 根据上述的结果是符合CAS的原理
    }
}
