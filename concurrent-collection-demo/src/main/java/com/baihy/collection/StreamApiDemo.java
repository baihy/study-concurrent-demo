package com.baihy.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy.collection
 * @description:
 * @author: huayang.bai
 * @date: 2019/08/23 9:54
 */
public class StreamApiDemo {


    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new StreamApiDemo().test();
        }
    }


    public void test() {
        List<String> list = new ArrayList<>();
        list.add("aaaa");
        list.add("bbbb");
        list.add("vvvv");
        list.add("dddd");
        list.add("ffff");
        list.add("gggg");
        list.add("hhhh");
        List<String> list1 = Collections.synchronizedList(new ArrayList<>());
        // 注意并行流底层使用的是多线程，进行操作的。把数据分割成若干个数据块
        list.parallelStream().forEach(str -> {
            try {
               Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(str);
        });
        System.out.println(Thread.currentThread().getName() + ":" + list + "****" + list1);
        System.out.println(list.size() + "********" + list1.size());
    }

}
