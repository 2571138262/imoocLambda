package com.imooc.lambda.imooclambda.demo1;

import java.util.stream.IntStream;

public class MInDemo {

    public static void main(String[] args) {
        int[] nums = {33, 55, -55, 90, -666, 90};
        
        int min = Integer.MAX_VALUE;
        for (int i:
            nums) {
            if (i < min){
                min = i;
            }
        }
        System.out.println(min);
        
        // jdk8
        /*
            parallel() 流会并行执行
         */
        int min2 = IntStream.of(nums).parallel().min().getAsInt();
        
        System.out.println(min2);
    }
    
}
