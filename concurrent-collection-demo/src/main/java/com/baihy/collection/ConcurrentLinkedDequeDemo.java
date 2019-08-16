package com.baihy.collection;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy.collection
 * @description:
 * @author: huayang.bai
 * @date: 2019/08/16 15:20
 */
public class ConcurrentLinkedDequeDemo {

    public static void main(String[] args) throws InterruptedException {
        // 非阻塞式的并发集合
        ConcurrentLinkedDeque<String> deque = new ConcurrentLinkedDeque<>();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    deque.add(Thread.currentThread().getName() + "：" + j);
                }
            });
            thread.start();
            thread.join();
        }
        System.out.println("********添加完毕*********");
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    if (j % 2 == 0) {
                        deque.pollFirst();  // 从队列的尾部进行移除
                    } else {
                        deque.pollFirst(); // 从队列的头部进行移除
                    }
                }
            });
            thread.start();
            thread.join();
        }
        System.out.println("********移除完毕*********");
    }
}
