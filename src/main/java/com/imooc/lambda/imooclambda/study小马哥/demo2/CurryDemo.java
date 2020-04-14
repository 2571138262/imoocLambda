package com.imooc.lambda.imooclambda.study小马哥.demo2;

import java.util.function.Function;

/**
 *  级联表达式和柯里化
 *         柯里化： 把多个参数的函数转化成为只有一个参数的函数
 *              目的：函数标准化
 *         
 */
public class CurryDemo {

    public static void main(String[] args) {

        /**
         * 实现了 x + y 的级联表达式
         */
        Function<Integer, Function<Integer, Integer>> function = x -> y -> x + y;

        System.out.println(function.apply(1).apply(2));
        
        Function<Integer, Function<Integer, Function<Integer, Integer>>> fun = x -> y -> z -> x + y + z;
        System.out.println(fun.apply(1).apply(2).apply(3));
        
        int[] nums = {1,2,3};
        Function f = fun;
        for (int i = 0; i < nums.length; i++) {
            if (f instanceof Function){
                Object a = f.apply(nums[i]);
                if (a instanceof Function){
                    f = (Function) a;
                }else{
                    System.out.println("结果为 ：" + a);
                }
            }
        }
    }
    
}
