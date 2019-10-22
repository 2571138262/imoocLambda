package com.imooc.lambda.imooclambda.demo2;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class FunctionDemo {

    public static void main(String[] args) {
        // 断言函数接口
        Predicate<Integer> predicate = i -> i > 0;
        IntPredicate predicate1 = i -> i > 0;
        System.out.println(predicate.test(1));
        
        // 消费函数接口
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("aaaaa");
        IntConsumer consumer2 = s -> System.out.println(s);
    }
    
}
