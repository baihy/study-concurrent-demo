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

