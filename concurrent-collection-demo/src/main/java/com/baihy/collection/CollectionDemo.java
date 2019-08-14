package com.baihy.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy.collection
 * @description:
 * @author: huayang.bai
 * @date: 2019-08-14 20:59
 */
public class CollectionDemo {

    /**
     * 集合在遍历的过程中，是不允许修改的。
     */

    public static void main(String[] args) {
        // 集合在遍历的过程中，是不允许修改的。
        List<String> list = new ArrayList<>(4);
        list.add("aaaa");
        list.add("bbbb");
        list.add("cccc");
        list.add("dddd");
        for (String str : list) {
            if ("bbbb".equals(str)) {
                boolean result = list.remove(str);
                System.out.println(list);
                System.out.println("移除的结果是：" + result);
            }
        }
    }
}
