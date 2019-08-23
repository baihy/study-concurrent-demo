package com.baihy.collection;

import java.util.ArrayList;
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
        new StreamApiDemo().test();
    }


    public void test(){
        List<String> list = new ArrayList<>();
        list.add("aaaa");
        list.add("bbbb");
        list.add("vvvv");
        list.add("dddd");
        list.add("ffff");
        list.add("gggg");
        list.add("hhhh");
        List<String> list1 = new ArrayList<>();
        list.parallelStream().forEach(str -> {
            list1.add(str);
        });
        System.out.println(list.size() + "********" + list1.size());
    }

}
