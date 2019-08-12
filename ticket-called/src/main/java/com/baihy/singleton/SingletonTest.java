package com.baihy.singleton;

import java.io.*;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy
 * @description: 理由序列化破解单例
 * @author: huayang.bai
 * @date: 2019/08/09 9:27
 */
public class SingletonTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        /**
         * 通过序列化和反序列化操作，破解单例模式，
         * 防止通过序列化和反序列化破解单例的办法是：实现readResolve()方法。
         *     private Object readResolve() {
         *         return singletonDemo;
         *     }
         */
        SingletonDemo singletonDemo = SingletonDemo.newInstance();
        System.out.println(singletonDemo);
        try (FileOutputStream fos = new FileOutputStream("abc.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(singletonDemo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileInputStream fis = new FileInputStream("abc.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            SingletonDemo singletonDemo1 = (SingletonDemo) ois.readObject();
            System.out.println(singletonDemo1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

