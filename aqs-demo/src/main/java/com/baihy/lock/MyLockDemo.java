package com.baihy.lock;

import java.util.concurrent.TimeUnit;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy.lock
 * @description:
 * @author: huayang.bai
 * @date: 2019/08/13 14:41
 */
public class MyLockDemo {

    private int m = 0;
    private MyLock lock = new MyLock();

    public int add() {
        int result = 0;
        lock.lock();
        result = m++;
        lock.unlock();
        return result; // 因为存在非原子性操作，肯定会出现数据不一致的问题。
    }

    public static void main(String[] args) {
        MyLockDemo demo = new MyLockDemo();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    // 这里让线程休眠是为了更能显示出多线程访问共享变量的数据不一致性问题。
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int add = demo.add();
                System.out.print(add + " ");
            }).start();
        }
    }


}


