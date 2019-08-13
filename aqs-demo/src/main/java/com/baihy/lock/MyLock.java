package com.baihy.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy.lock
 * @description:
 * @author: huayang.bai
 * @date: 2019/08/13 14:41
 */
public class MyLock implements Lock {

    // 我们要实现锁：就是对Lock接口中的抽象方法进行实现。

    private MyAQS myAQS = new MyAQS();

    private class MyAQS extends AbstractQueuedSynchronizer {
        // 获取锁的方法
        @Override
        protected boolean tryAcquire(int arg) {
            /**
             * 获取锁的的操作分为两步：1.修改共享变量。2.设置当前线程占用资源
             */
            int state = this.getState();
            // 获取对象的状态，如果状态为0表示对象没有被任何线程占用，如果状态大于0，则表示对象已经被其他线程占用
            if (state == 0) {
                // 当状态为0时，表示对象没有被他线程占用， 那么当前线程可以占用对象。
                // 那么对象占用之后，要告诉其他线程，该对象已经被占用了，就需要修改state的值
                // 这里为了保证原子性操作，所以这里需要是用CAS原理，原子变量来修改。
                boolean compareAndSetState = compareAndSetState(0, arg);// 通过CAS的原子操作
                // 两个参数的值是：期望值和目标值。
                if (compareAndSetState) {
                    // 这是共享变量成功之后，设置当前线程占用资源
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            }
            return false;
        }

        /**
         * 获取锁和释放锁的两个方法中的参数arg，可以理解为state的变化量，表示的含义就是：当获取锁的时候，state会加arg，而释放锁是就要减arg。
         * 如果我们直接确定state的变化量，arg参数就没有意义了
         */

        // 释放锁的方法
        @Override
        protected boolean tryRelease(int arg) {
            /**
             * 释放锁只需要一个步骤即可。获取到state对象，改变state的值即可。
             */
            int state = this.getState();
            if ((state - arg) == 0) { // 表示的含义是：释放锁时候，可不可以被其他线程占用
                setExclusiveOwnerThread(null); // 把占用对象的线程设置为null
                setState(state - arg);
                return true;

            }
            setState(state - arg);
            // 这里是没有线程安全问题，因为释放锁之前，已经独占了state。
            // 注意：这里不能直接设置为0，因为存在线程重入的问题。
            return false;
        }

        // 这个方法的含义：待查
        public Condition newConditionObject() {
            return new ConditionObject();
        }
    }


    @Override
    public void lock() {
        // 获取锁。
        // 这里是强制独占锁
        myAQS.acquire(1); // 1的作用就是在获取锁的时候，state的值会加1。必须要和是释放锁相对应。
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        // 如果当前线程未被中断，则获取锁。
        myAQS.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        // 尝试获取锁，如果获取到的锁，返回true，如果没有获取到锁，返回false
        return myAQS.tryAcquire(1); // 1的作用就是在获取锁的时候，state的值会加1。必须要和是释放锁相对应。
    }


    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        // 如果锁在给定的等待时间内空闲，并且当前线程未被中断，则获取锁。
        return myAQS.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        //释放锁。
        myAQS.release(1); // 1的作用就是在释放锁的时候，state的值会减1。必须要和是获取锁相对应。
    }

    @Override
    public Condition newCondition() {
        // 返回绑定到此Lock实例的新Condition实例。
        return myAQS.newConditionObject();
    }
}


