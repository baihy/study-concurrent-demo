package com.baihy.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy.singleton
 * @description: 枚举的方式实现单例模式
 * @author: huayang.bai
 * @date: 2019/08/09 14:57
 */
public class EnumSingletonDemo {

    // 利用的原理是枚举的元素INSTACCE是EnumSingleton类型的对象
    // 保证单例用的是 枚举元素常量的，在加载枚举类型的时候，只实例化一次的特点。
    private enum EnumSingleton {
        INSTACCE;  // 常量，在加载的时候，被实例化一次。常量是在共享区
        // 通过内部的枚举类的元素类型是枚举类型本身的对象，我们在调用相关的方法。实现了单例，
        // 如何保证单例呢？ 因为枚举对象是单例的。
        private EnumSingletonDemo instance = null;

        // 在构造方法中初始化singletonDemo对象
        EnumSingleton() {
            instance = new EnumSingletonDemo();
        }
    }

    /**
     * 内部枚举类型实现了懒加载
     */

    // 私有化构造方法
    private EnumSingletonDemo() {
        // 如何防止通过反射来破解呢？
    }

    public static EnumSingletonDemo newInstance() {
        return EnumSingleton.INSTACCE.instance;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        /*for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(EnumSingletonDemo.newInstance());
            }).start();
        }*/

        Class<EnumSingletonDemo> enumSingletonDemoClass = EnumSingletonDemo.class;
        Constructor<EnumSingletonDemo> constructor = enumSingletonDemoClass.getDeclaredConstructor();
        EnumSingletonDemo enumSingletonDemo1 = constructor.newInstance();
        EnumSingletonDemo enumSingletonDemo2 = constructor.newInstance();
        System.out.println(enumSingletonDemo1);
        System.out.println(enumSingletonDemo2);
    }
}
