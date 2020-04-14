package com.imooc.lambda.imooclambda.study小马哥.demo1;

import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class FunctionDemo {
 
    public static void main(String[] args) {
        // 断言函数接口
        IntPredicate intPredicate = i -> 2 * i > 1;
        Predicate<Integer> predicate = i -> i > 0;
        System.out.println(predicate.test(-8));
        System.out.println(intPredicate.test(1));
        
        // 消费的接口
        IntConsumer intConsumer = s -> {
            s ++;
            System.out.println(s);
        };
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("输入的数据");
        
        intConsumer.accept(5);
    } 
    
    
}
