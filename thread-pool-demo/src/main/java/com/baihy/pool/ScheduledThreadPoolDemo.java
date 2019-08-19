package com.baihy.pool;

import com.baihy.task.ThreadTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy.pool
 * @description: 创建任务调度线程池
 * @author: huayang.bai
 * @date: 2019/08/19 16:14
 */
public class ScheduledThreadPoolDemo {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; i++) {  // 创建10个任务
            // 创建任务对象
            ThreadTask threadTask = new ThreadTask();
            // 把任务交给线程池，来执行任务
            scheduledExecutorService.execute(threadTask);
            scheduledExecutorService.schedule(threadTask, 1, TimeUnit.SECONDS); // 通过任务调度来执行任务
        }
        // 当所有线程执行完之后，停止线程池。
        scheduledExecutorService.shutdown();
    }

}
