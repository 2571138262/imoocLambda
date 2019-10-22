package com.imooc.lambda.imooclambda.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 变量引用
 */

public class VarDemo {

    public static void main(String[] args) {
        final String str = "我们的时间";
//        str = "";
        // JDK8 之后内部类中调用外部变量可以不用加final修饰， 但是这个变量仍然必须为final或者实际上是final（赋值以后不被修改）
        Consumer<String> consumer = s -> System.out.println(s + str);
        consumer.accept("12121");

        /**
         * 重点探讨;    为什么匿名类引用外边的变量要使用final修饰
         *      java中的值传递: 传递是某个对象-应用的地址，也就是说这个外部的地址， 
         *                      不论怎么变化都不会影响方法中传递进去的地址的指向
         *      内部类调用外部变量:  内部类调用外部变量的时候，内部类中传入的也是某个对象的引用，
         *                      所有当外部地址存在变化的时候，会导致内部类中的变量和外部的变量不指向同一个对象， 存在二义性         
         */
        List<String> list = new ArrayList<>();
        consumer = s -> System.out.println(list + s);
        consumer.accept("1223145");
        
        
    }

}
