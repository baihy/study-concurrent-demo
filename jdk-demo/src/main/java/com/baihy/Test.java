package com.baihy;

/**
 * @projectName: study-concurrent-demo
 * @packageName: com.baihy
 * @description:
 * @author: huayang.bai
 * @date: 2019/8/31 1:26 下午
 */
public class Test {

    public void scan() {
        String rootPath = this.getClass().getResource("/").getPath();
        System.out.println(rootPath);
    }

}
