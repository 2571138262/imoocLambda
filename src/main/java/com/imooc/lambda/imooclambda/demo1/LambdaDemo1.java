package com.imooc.lambda.imooclambda.demo1;

/**
 * @FunctionalInterface 这个注解表明这个接口是一个函数式接口， 不可以在里边加其他要实现的方法了 
 *      -- 单一责任制思想，接口要尽量小， 只有一个方法，只干一件事
 */
@FunctionalInterface 
interface  interface1{
    int doubleNumber(int i);
    
    // 默认实现方法
    default int add(int a , int b){
        a = this.doubleNumber(a);
        return a + b;
    }
    
}

/**
 * @FunctionalInterface 这个注解表明这个接口是一个函数式接口， 不可以在里边加其他要实现的方法了 
 *      -- 单一责任制思想，接口要尽量小， 只有一个方法，只干一件事
 */
@FunctionalInterface
interface  interface2{
    int doubleNumber(int i);

    // 默认实现方法
    default int add(int a , int b){
        
        return a + b;
    }
}

@FunctionalInterface
interface  interface3 extends interface1, interface2{
    @Override
    default int add(int a, int b) {
        return interface1.super.add(a, b);
    }
}


/**
 * 
 * Lambda表达式返回了一个要实现指定接口的对象实例(有限制)
 *              接口中只有一个要实现的方法  -- 函数接口
 */
public class LambdaDemo1 {

    public static void main(String[] args) {
        interface1 i1 = (i) -> i * 2;
        // 这种是最常见的写法
        interface1 i2 = i -> i * 2;

        interface1 i3 = (int i) -> i * 2;

        interface1 i4 = (int i) -> {
            System.out.println("--- --");
            return i * 2;
        };

        
        
        System.out.println(i1.doubleNumber(1));
        System.out.println(i1.add(1, 2));
        
    }
    
}
