package com.imooc.lambda.imooclambda.study小马哥.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

/**
 * 方法应用
 */

class Dog{
    public Dog() {
    }

    public Dog(String name) {
        this.name = name;
    }

    private String name = "哮天犬";

    /**
     * 默认10斤
     */
    private int food = 10;

    /**
     * 狗叫    静态方法
     *      有输入没有输出， 消费者
     * @param dog
     */
    public static void bark(Dog dog){
        System.out.println(dog + "叫了");
    }

    /**
     * 吃狗粮
     * @param num
     * @return 还剩下多少
     *      有输入有输出   --  函数
     *      JDK 默认会把当前实例传入到非静态方法，参数名为this，位置是第一个
     *      public int eat(Dog this,int num)
     */
    public int eat(int num){
        System.out.println("吃了 " + num + " 斤狗粮");
        this.food -= num;
        return this.food;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
public class MethodReferenceDemo {

    public static void main(String[] args) {
        Dog dog = new Dog();
        
        dog.eat(3);
        
        /**
         * Lambda 表达式是一个匿名函数， s是函数的参数， sout是执行体
         */
        Consumer<String> consumer = s -> System.out.println(s);
        /**
         * 方法引用
         *      当函数执行体中只有一个函数调用，而且函数的参数和箭头左边是一样的，这个时候可以缩写，缩写成方法引用的方式
         */
        Consumer<String> consumer1 = System.out::println;

        consumer1.accept("this is data");

        /**
         * 静态方法的方法引用
         */
        Consumer<Dog> consumer21 = dog1 -> Dog.bark(dog1);
        Consumer<Dog> consumer22 = Dog::bark;
        
        consumer22.accept(dog);

        /**
         *  非静态方法，使用对象实例的方法引用
         *      函数是有输出的
         */
//        Function<Integer, Integer> function = dog::eat;
//        System.out.println("还剩下  " + function.apply(2) + "斤");
        /**
         * 输入和输出类型一样，可以改成一元函数
         */
        //UnaryOperator<Integer> function = dog::eat;
//        System.out.println("还剩下  " + function.apply(2) + "斤");
        /**
         * JDK 封装了基本数据类型
         */
        IntUnaryOperator function = dog::eat;
//        dog = null;
        System.out.println("还剩下 " + function.applyAsInt(2) + " 斤");

        /**
         * 直接使用类名访问非静态方法 的方法引用
         *      JDK会默认为成员方法添加一个当前实例的参数， 参数名为this， 位置为第一个
         */
        BiFunction<Dog, Integer, Integer> biFunction = Dog::eat;
        System.out.println("BiFunction--还剩下" + biFunction.apply(dog, 2) + "斤");

        /**
         * 构造函数的方法引用(没有参数) 
         */
        Supplier<Dog> supplier = Dog::new;
        System.out.println("创建了新对象" + supplier.get());
        System.out.println();

        /**
         * 带参数的构造函数的方法引用
         */
        Function<String, Dog> function2 = Dog::new;

        System.out.println("Function 创建了新对象" + function2.apply("旺财"));
        
        
        ArrayList<String> list = new ArrayList<>();
        test(list);
        System.out.println("List" + list);
        
        int a = 1;
        test1(a);
        System.out.println("----" + a);
    }
    
    private static void test(List<String> list){
        list = null;
    }
    
    private static void test1(int a){
        a = 3;
    }

}
