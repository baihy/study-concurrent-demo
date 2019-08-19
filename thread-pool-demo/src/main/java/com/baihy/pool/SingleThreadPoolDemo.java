package com.baihy.pool;

import com.baihy.task.ThreadTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy.pool
 * @description:
 * @author: huayang.bai
 * @date: 2019/08/19 16:12
 */
public class SingleThreadPoolDemo {


    public static void main(String[] args){
        // 创建一个单个线程的线程池。当存在多个任务时，多余的任务会进入等待队列，等待执行。
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {  // 创建10个任务
            // 创建任务对象
            ThreadTask threadTask = new ThreadTask();
            // 把任务交给线程池，来执行任务
            executorService.execute(threadTask);
        }
        // 当所有线程执行完之后，停止线程池。
        executorService.shutdown();
    }

}
