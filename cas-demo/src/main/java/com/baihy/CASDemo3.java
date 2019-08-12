package com.baihy;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy
 * @description:
 * @author: huayang.bai
 * @date: 2019-08-11 17:12
 */
public class CASDemo3 {
    // 定义一个拥有初始值和版本号的的原子变量
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            boolean b = atomicStampedReference.compareAndSet(100, 110, 1, 2);
            // 四个参数的含义是：原子变量值的期望值，原子变量要修改的值，原子变量期望的版本，原子变量修改值之后的版本号
            System.out.println("100改为110是否成功:" + b);
            System.out.println("原子变量最后的值是：" + atomicStampedReference.getReference());
            System.out.println("原子变量最后的值的版本号是：" + atomicStampedReference.getStamp());
        }).start();
        System.out.println("***************************************************************************");
        new Thread(() -> {
            boolean b = atomicStampedReference.compareAndSet(110, 100, 2, 1);
            // 四个参数的含义是：原子变量值的期望值，原子变量要修改的值，原子变量期望的版本，原子变量修改值之后的版本号
            System.out.println("110改为100是否成功:" + b);
            System.out.println("原子变量最后的值是：" + atomicStampedReference.getReference());
            System.out.println("原子变量最后的值的版本号是：" + atomicStampedReference.getStamp());
        }).start();
        System.out.println("***************************************************************************");
        new Thread(() -> {
            // 因为期望值不对，所以修改失败。
            boolean b = atomicStampedReference.compareAndSet(110, 120, 2, 3);
            // 四个参数的含义是：原子变量值的期望值，原子变量要修改的值，原子变量期望的版本，原子变量修改值之后的版本号
            System.out.println("110改为120是否成功:" + b);
            System.out.println("原子变量最后的值是：" + atomicStampedReference.getReference());
            System.out.println("原子变量最后的值的版本号是：" + atomicStampedReference.getStamp());
        }).start();
    }
}
