package com.baihy.pool;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy.pool
 * @description:
 * @author: huayang.bai
 * @date: 2019/08/19 17:44
 */
public class StateDemo {

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;
    private static final int RUNNING = -1 << COUNT_BITS;
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    private static final int STOP = 1 << COUNT_BITS;
    private static final int TIDYING = 2 << COUNT_BITS;
    private static final int TERMINATED = 3 << COUNT_BITS;

    public static void main(String[] args) {
        System.out.println("CAPACITY: " + Integer.toBinaryString(CAPACITY) + ":" + Integer.toBinaryString(CAPACITY).length());
        System.out.println("RUNNING: " + Integer.toBinaryString(RUNNING) + ":" + Integer.toBinaryString(RUNNING).length());
        System.out.println("SHUTDOWN: " + Integer.toBinaryString(SHUTDOWN) + ":" + Integer.toBinaryString(SHUTDOWN).length());
        System.out.println("STOP: " + Integer.toBinaryString(STOP) + ":" + Integer.toBinaryString(STOP).length());
        System.out.println("TIDYING: " + Integer.toBinaryString(TIDYING) + ":" + Integer.toBinaryString(TIDYING).length());
        System.out.println("TERMINATED: " + Integer.toBinaryString(TERMINATED) + ":" + Integer.toBinaryString(TERMINATED).length());
    }

}
