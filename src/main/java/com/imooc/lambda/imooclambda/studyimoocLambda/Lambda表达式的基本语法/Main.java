package com.imooc.lambda.imooclambda.studyimoocLambda.Lambda表达式的基本语法;

/**
 * Lambda表达式的基本语法
 */
public class Main {

    public static void main(String[] args) {
        /**
         * TODO:####（1）、Lambda表达式基本语法
         * * 声明：就是和lambda表达式绑定的接口类型
         * * 参数：包含在一堆圆括号中，和绑定的接口中的抽象方法中的参数个数及顺序一致
         * * 操作符：-> 
         * * 执行代码块：包含在一对大括号中，出现在操作符号的右侧
         * ###### [接口声明] = (参数) -> {执行代码块};
         */
        
        ILambda1 i1 = () -> {
            System.err.println("Hello imooc");
            System.err.println("Welcome imooc");
        };
        i1.test();
        ILambda1 i2 = () -> System.err.println("Hello imooc");
        i2.test();
        
        ILambda2 i21 = (String n, int a) -> {
            System.err.println(n + " say : my year'old is : " + a);  
        };
        i21.test("jerry", 18);
        ILambda2 i22 = (n, a) -> {
            System.err.println(n + " say : my year'old is : " + a);
        };
        i22.test("wo", 1);
        
        ILambda3 i31 = (x, y) -> {
            int z = x + y;
            return z;
        };
        System.err.println(i31.test(11, 22));
        ILambda3 i32 = (x, y) -> x + y;
        System.err.println(i31.test(11, 22));
    }
    
    // TODO: 没有参数，没有返回值的lambda表达式绑定的接口
    interface ILambda1{
        void test();
    }
    
    // TODO: 带有参数，没有返回值的Lambda表达式
    interface ILambda2{
        void test(String name, int age);
    }
    
    // TODO: 带有参数，带有返回值的Lambda表达式
    interface ILambda3{
        int test(int x, int y);
    }
}
