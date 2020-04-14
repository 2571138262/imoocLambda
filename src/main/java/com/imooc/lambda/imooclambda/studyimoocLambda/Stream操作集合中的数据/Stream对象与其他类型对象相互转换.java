package com.imooc.lambda.imooclambda.studyimoocLambda.Stream操作集合中的数据;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream对象与其他类型对象相互转换 {

    public static void main(String[] args) {
        // TODO 1、批量数据 -> Stream对象
        // TODO 多个数据
        Stream stream = Stream.of("admin", "tom", "damu");
        
        // TODO 数组
        String[] strArrays = new String[]{"xueqi", "biyao"};
        Stream<String> stream1 = Arrays.stream(strArrays);
        
        // TODO 列表
        List<String> list = new ArrayList<>();
        list.add("少林");
        list.add("武当");
        list.add("崆峒");
        Stream stream3 = list.stream();
        
        // TODO 集合
        Set<String> set = new HashSet<>();
        set.add("少林");
        set.add("武当");
        set.add("崆峒");
        Stream stream4 = set.stream();
        
        // TODO Map
        Map<String, Integer> map = new HashMap<>();
        map.put("tom", 1000);
        map.put("jerry", 1200);
        map.put("shuke", 1000);
        Stream stream5 = map.entrySet().stream();
        
        // TODO 2、Stream对象对于基本数据类型封装
        // TODO int / long / double
//        IntStream.of(new int[]{10, 20, 30}).forEach(System.err::println);
//        IntStream.range(1, 5).forEach(System.err::println);
//        IntStream.rangeClosed(1, 5).forEach(System.err::println);
//        
//        // TODO 3、Stream 对象 --> 转换得到指定的数据类型
//        // TODO 数组
//        Object[] objx = stream.toArray(String[]::new);
//        
//        // TODO 字符串
//        String str = stream.collect(Collectors.joining()).toString();
//        System.err.println(str);
//        
//        // TODO 列表
//        List<String> listx = (List<String>) stream.collect(Collectors.toList());
//        System.err.println(listx);
//        
//        // TODO 集合
//        Set<String> setx = (Set<String>) stream.collect(Collectors.toSet());
//        System.err.println(setx);
//
//        // TODO Map
//        Map<String, String> mapx = (Map<String, String>) stream.collect(Collectors.toMap(x -> x, y -> "value"));
//        System.err.println(mapx);
//        
        // TODO 4、Stream中常见的API操作
        
        List<String> accountList = new ArrayList<>();
        accountList.add("xongjiang");
        accountList.add("lujunyi");
        accountList.add("wuyong");
        accountList.add("linchong");
        accountList.add("luzhishen");
        accountList.add("likui");
        accountList.add("wusong");
        
        // TODO map() 中间操作， map() 方法接收一个Function接口
//        accountList = accountList.stream().map(x -> "梁上好汉：" + x).collect(Collectors.toList());
        
        // TODO filter() 添加过滤条件，过滤符合条件的用户
        accountList = accountList.stream().filter(x -> x.length() > 5).collect(Collectors.toList());
        
        // TODO forEach 增强型循环
//        accountList.forEach(x -> System.err.println("forEach -> " + x));
//        accountList.forEach(x -> System.err.println("forEach -> " + x));
//        accountList.forEach(x -> System.err.println("forEach -> " + x));

//        accountList.forEach(System.err::println);
        
        // TODO peek() 中间操作，迭代数据完成数据的依次处理过程
//        accountList.stream()
//                .peek(x -> System.err.println("peek 1: " + x))
//                .peek(x -> System.err.println("peek 2: " + x))
//                .forEach(System.err::println);
        
        // TODO Stream中对于数字运算的支持
        List<Integer> intList = new ArrayList<>();
        intList.add(20);
        intList.add(19);
        intList.add(7);
        intList.add(8);
        intList.add(86);
        intList.add(2);
        intList.add(20);
        
        // TODO skip() 中间操作，有状态，跳过部分数据 
//        intList.stream().skip(3).forEach(System.err::println);
        
        // TODO limit() 中间操作，有状态，限制输出数据数量
//        intList.stream().skip(3).limit(2).forEach(System.err::println);
        
        // TODO distinct() 中间操作, 有状态，剔除重复的数据
//        intList.stream().distinct().forEach(System.err::println);
        
        // TODO sorted() 中间操作，有状态，排序
        
        
        // TODO max() 获取最大值
        Optional<Integer> optional = intList.stream().max((x, y) -> x - y);
        System.err.println(optional.get());
        
        // TODO min() 获取最小值
        
        // TODO reduce() 合并处理数据
        Optional<Integer> optional2 = intList.stream().reduce((sum, x) -> sum + x);
        System.err.println(optional2.get());
    }
    
    
    
}
