package com.baihy.demo;

import java.util.concurrent.TimeUnit;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy.demo
 * @description:
 * @author: huayang.bai
 * @date: 2019/08/06 17:48
 */
public class SynchDemo {

    //synchronized 修饰非静态方法
    public synchronized void accessResources0() {
        try {
            // TimeUnit.SECONDS.sleep(1);
            TimeUnit.MINUTES.sleep(2);  // 这是线程休眠等待2分钟
            System.out.println(Thread.currentThread().getName() + " is running");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //synchronized 修饰静态方法
    public synchronized static void accessResources1() {
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + " is running");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //synchronized 修饰的this指的是调用当前方法的对象
    public void accessResources2() {
        // 锁定是调用这个方法的对象，锁定的是一个对象
        synchronized (this) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + " is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * synchronized修饰对象this和Class对象的区别：
     *
     */
    //synchronized 修饰Class对象
    public void accessResources3() {
        // 锁定是SynchDemo类型的所有对象，锁定的是所有的对象。
        synchronized (SynchDemo.class) {
            // 当类加载器把class文件加载在方法区时候，会在堆内存中实例化出一个Class类型的对象。
            // 这里就是拿到了堆内存中的Class类型的对象，就相当于拿到了所有的SynchDemo类型的实例化对象。所有的SynchDemo类型的对象，使用共同的一个锁。
            // 这里锁定的是，堆内存中Class类型的对象，那么由这个Class类型的对象产生的所有SynchDemo类型的对象，共用这一把锁。
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + " is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        SynchDemo demo = new SynchDemo();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                demo.accessResources0();
                // SynchDemo.accessResources1();
                // demo.accessResources2();
                // demo.accessResources3();
            }).start();
        }
    }


}
