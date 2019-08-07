package com.baihy.demo;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy.demo
 * @description:
 * @author: huayang.bai
 * @date: 2019/08/06 16:37
 */
public class TicketDemo extends Thread {
    private int index = 1;
    private static final int MAX_INDEX = 50;

    @Override
    public void run() {
        // 虽说拿到的是static变量是共享变量，但是由于run方法中的操作不是原子性操作，所以才会出现跳号、重号、超过最大值的现象。
        synchronized (this) {
            while (index <= MAX_INDEX) {
                Integer num = index++;
                System.out.println(Thread.currentThread().getName() + "叫了" + num + "号");
            }
            /**
             * 内存的分析过程是：
             *  1.从主内存中读取index
             *  2.对index做加1操作
             *  3.把修改之后的index变量刷新到主内存
             *  4.打印输出。
             */
            // 为了保证数据的一致性，我们需要保证以上四步作为原子性操作
            // JAVA中如何保证多步骤聚合在一起是原子性操作呢？我们需要使用synchronized关键字
        }
    }

    public static void main(String[] args) {
        // 总共定义了4台叫号机
        new TicketDemo().start();
        new TicketDemo().start();
        new TicketDemo().start();
        new TicketDemo().start();
    }

}
