package com.imooc.lambda.imooclambda.study小马哥.demo1;

import java.util.function.Function;

/**
 * 级联表达式和柯里化
 *      
 *      级联表达式：多个箭头（->）的lambda表达式
 *      柯里化    ：把多个参数的函数转化为只有一个参数的函数
 *      柯里化的目的：函数标准化
 *      高阶函数：返回函数的函数
 * 
 */
public class CurryDemo {

    public static void main(String[] args) {
        /**
         * lambda 表达式 -> 左边是输入， 右边是输出
         * 
         *  1、整体是一个函数，输入了一个x类型，返回了一个 y -> x + y 函数
         *  2、返回结果函数，输入了一个y类型， 返回了一个 x + y 结果
         *  
         *  
         *  
         */
        Function<Integer, Function<Integer, Integer>> fun = x -> y -> x + y;

        System.out.println(fun.apply(2).apply(3));
        
        
        Function<Integer, Function<Integer, Function<Integer, Integer>>> function = 
                x -> y -> z -> x + y + z;
        System.out.println(function.apply(1).apply(2).apply(3));
        
        int[] nums = {2, 3, 4};
        Function f = function;
        for (int i = 0; i < nums.length; i++) {
            Object obj = f.apply(nums[i]);
            if (obj instanceof Function){
                f = (Function) obj;
            }else{
                System.out.println("调用结束：" + obj);
            }
        }
    }
    
}
