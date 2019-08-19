package com.baihy.pool;

import com.baihy.task.ThreadTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy.pool
 * @description:
 * @author: huayang.bai
 * @date: 2019/08/19 16:09
 */
public class CacheThreadPoolDemo {

    public static void main(String[] args){
        //创建一个可变大小的线程池，会根据任务的数量，来实例化线程。
        ExecutorService executorService = Executors.newCachedThreadPool();
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
