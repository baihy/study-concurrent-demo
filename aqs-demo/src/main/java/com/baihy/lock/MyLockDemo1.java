package com.baihy.lock;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy.lock
 * @description:
 * @author: huayang.bai
 * @date: 2019/08/13 14:41
 */
public class MyLockDemo1 {
    private int m = 0;
    private MyLock lock = new MyLock();
    public void a() {
        lock.lock();
        System.out.println("a");
        b();
        lock.unlock();
    }
    /**
     * 在a方法和b方法中，我们都加了锁，但是，我们使用的通过对象demo，这种情况下，叫做线程重入，不能出现线程死锁。
     * 方法a和方法b是同一把锁。当我们获取的是同一把锁时，锁是具有可重入性。
     * 可重入性：同一个锁多统一资源进行占有的时候，直接分配给这个线程
     *  那么解决办法是：在我们定义的锁作用，添加上可重入锁的支持。可重入锁是：
     *                  在获取锁的方法中，添加判断条件即可。
     *              if (getExclusiveOwnerThread() == Thread.currentThread()) { // 增加锁的可重入性
     *                 // 同一把锁，占用同一资源时，直接分配给这个线程
     *                 setState(getState() + arg); // 因为此时占用资源的线程已经是当前线程了，所以说，不需要设置占用资源的线程了，只需要更改state即可
     *                 return true;
     *             }
     */
    public void b() {
        lock.lock();
        System.out.println("b");
        lock.unlock();
    }
    public static void main(String[] args) {
        MyLockDemo1 demo = new MyLockDemo1();
        new Thread(() -> {
            demo.a();
        }).start();
    }


}


