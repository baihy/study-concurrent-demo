package com.baihy.task;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy.task
 * @description:
 * @author: huayang.bai
 * @date: 2019/08/19 16:01
 */
public class ThreadTask implements Runnable {

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + "开始执行任务");
        /*try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadName + "任务执行完毕*******");*/
    }
}
