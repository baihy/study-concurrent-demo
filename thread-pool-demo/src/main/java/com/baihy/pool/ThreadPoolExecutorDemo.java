package com.baihy.pool;

import com.baihy.task.ThreadTask;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy
 * @description:
 * @author: huayang.bai
 * @date: 2019/08/23 11:10
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        int capacity = 4;
        LinkedBlockingQueue<Runnable> runnables = new LinkedBlockingQueue<>(capacity);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 6,
                0L, TimeUnit.MILLISECONDS,
                runnables);
        /**
         * 注意：
         *  1.corePoolSize是核心线程池的大小，
         *  2.maximumPoolSize是线程池的大小。
         *  3.capacity：线程队列的大小。
         *  下面循环中添加10个任务：
         *      a.添加前三个任务时，会调用核心线程池的线程执行任务。
         *      b.此时corePoolSize指定的线程已经用完了，而线程数又小于maximumPoolSize指定的线程数，
         *       而线程队列为null，当第四个任务来的时候<b>不会去开辟新的线程，而是任务会直接进入队列中，只有当队列满了，
         *       此时线程数又小于maximumPoolSize指定的线程数，此时才会去新建线程，直到大小maximumPoolSize抛出异常<b/>。
         *  综上所述：
         *      线程池，优先使用核心线程，当核心线程用完之后，任务会放进队列中，当核心线程数用完和阻塞队列满了的时候
         *      判断线程数是否大于maximumPoolSize，大于抛出异常，小于的话，才会新建线程。
         */


        for (int i = 0; i < 10; i++) {  // 创建10个任务
            // 创建任务对象
            ThreadTask threadTask = new ThreadTask();
            // 把任务交给线程池，来执行任务
            threadPoolExecutor.execute(threadTask); // 立刻执行任务方法
            System.out.println(i + ":" + runnables.size());
        }
        threadPoolExecutor.shutdown();

    }
}
