package com.imooc.lambda.imooclambda.studyimoocLambda.Stream线程安全问题;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        // 整数列表
        List<Integer> lists = new ArrayList<>();
        // 增加数据
        for (int i = 0; i < 1000; i++) {
            lists.add(i);
        }
        
        // 串行Stream
        List<Integer> list2 = new ArrayList<>();
        lists.stream().forEach(x -> list2.add(x));
        System.err.println(lists.size());
        System.err.println(list2.size());
        // 并行Stream
        List<Integer> list3 = new ArrayList<>();
        lists.parallelStream().forEach(x -> list3.add(x));
        System.err.println(list3.size());
        // 
        List<Integer> list4 = lists.parallelStream().collect(Collectors.toList());
        System.err.println(list4.size());
    }
    
}
