package com.imooc.lambda.imooclambda.studyimoocLambda.Lambda和Stream性能问题;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        Random random = new Random();

        // TODO 1、基本数据类型：整形
        List<Integer> integerList = new ArrayList<>();

        for (int i = 0; i < 1000000; i++) {
            integerList.add(random.nextInt(Integer.MAX_VALUE));
        }

//        // TODO 1）、Stream
//        testStream(integerList);
//        // TODO 2）、parallelStream
//        testParallelStream(integerList);
//        // TODO 3）、普通for
//        testForLoop(integerList);
//        // TODO 4）、增强型for
//        testStrongForLoop(integerList);
//        // TODO 5）、迭代器
//        testIterator(integerList);


        // TODO 2、复杂数据类型：对象
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            productList.add(new Product("pro " + i, i, random.nextInt(Integer.MAX_VALUE)));
        }
        
        // TODO 调用执行
        testProductStream(productList);
        testProductParallelStream(productList);
        testProductForLoop(productList);
        testProductStrongForLoop(productList);
        testProductIterator(productList);
    }

    public static void testStream(List<Integer> list) {
        long start = System.currentTimeMillis();

        Optional<Integer> optional = list.stream().max(Integer::compareTo);
        System.err.println(optional.get());

        long end = System.currentTimeMillis();
        System.err.println("testStream: " + (end - start) + " ms");
    }

    public static void testParallelStream(List<Integer> list) {
        long start = System.currentTimeMillis();

        Optional<Integer> optional = list.parallelStream().max(Integer::compareTo);
        System.err.println(optional.get());

        long end = System.currentTimeMillis();
        System.err.println("testParallelStream: " + (end - start) + " ms");
    }

    public static void testForLoop(List<Integer> list) {
        long start = System.currentTimeMillis();

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < list.size(); i++) {
            int current = list.get(i);
            if (current > max)
                max = current;
        }
        System.err.println(max);

        long end = System.currentTimeMillis();
        System.err.println("testForLoop: " + (end - start) + " ms");
    }

    public static void testStrongForLoop(List<Integer> list) {
        long start = System.currentTimeMillis();

        int max = Integer.MIN_VALUE;
        for (Integer integer : list) {
            if (integer > max)
                max = integer;
        }
        System.err.println(max);

        long end = System.currentTimeMillis();
        System.err.println("testStrongForLoop: " + (end - start) + " ms");
    }

    public static void testIterator(List<Integer> list) {
        long start = System.currentTimeMillis();

        Iterator<Integer> iterator = list.iterator();
        int max = iterator.next();
        while (iterator.hasNext()) {
            int current = iterator.next();
            if (current > max)
                max = current;
        }
        System.err.println(max);

        long end = System.currentTimeMillis();
        System.err.println("testIterator: " + (end - start) + " ms");
    }

    // TODO ----------------------------------------------------

    public static void testProductStream(List<Product> list) {
        long start = System.currentTimeMillis();

        Optional<Product> optional = list.stream().max(Comparator.comparingInt(p -> p.hot));
        System.err.println(optional.get());

        long end = System.currentTimeMillis();
        System.err.println("testProductStream: " + (end - start) + " ms");
    }

    public static void testProductParallelStream(List<Product> list) {
        long start = System.currentTimeMillis();

        Optional<Product> optional = list.parallelStream().max(Comparator.comparingInt(p -> p.hot));
        System.err.println(optional.get());

        long end = System.currentTimeMillis();
        System.err.println("testProductParallelStream: " + (end - start) + " ms");
    }

    public static void testProductForLoop(List<Product> list) {
        long start = System.currentTimeMillis();

        Product maxHot = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            Product current = list.get(i);
            if (current.hot > maxHot.hot)
                maxHot = current;
        }
        System.err.println(maxHot);

        long end = System.currentTimeMillis();
        System.err.println("testProductForLoop: " + (end - start) + " ms");
    }

    public static void testProductStrongForLoop(List<Product> list) {
        long start = System.currentTimeMillis();

        Product maxHot = list.get(0);
        for (Product product : list) {
            if (product.hot > maxHot.hot)
                maxHot = product;
        }
        System.err.println(maxHot);


        long end = System.currentTimeMillis();
        System.err.println("testProductStrongForLoop: " + (end - start) + " ms");
    }

    public static void testProductIterator(List<Product> list) {
        long start = System.currentTimeMillis();

        Iterator<Product> iterator = list.iterator();
        Product maxHot = iterator.next();
        while (iterator.hasNext()){
            Product current = iterator.next();
            if (current.hot > maxHot.hot)
                maxHot = current;
        }
        System.err.println(maxHot);

        long end = System.currentTimeMillis();
        System.err.println("testProductIterator: " + (end - start) + " ms");
    }

}

class Product {
    String name;
    Integer stock;
    Integer hot;

    public Product(String name, Integer stock, Integer hot) {
        this.name = name;
        this.stock = stock;
        this.hot = hot;
    }
}
