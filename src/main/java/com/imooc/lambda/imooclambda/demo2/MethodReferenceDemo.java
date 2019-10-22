package com.imooc.lambda.imooclambda.demo2;

import javax.sound.midi.Soundbank;
import java.util.function.*;

/**
 * lambda表达式方法引用
 */
public class MethodReferenceDemo {

    public static void main(String[] args) {

        Consumer<String> c = s -> System.out.println("s");
        /**
         * 方法引用
         */
        Consumer<String> consumer = System.out::println;
        consumer.accept("date");
        
        
        // 静态方法的方法引用
        Dog dog = new Dog();
        Consumer<Dog> consumer1 = Dog::bark;
        consumer1.accept(dog);
        
        // 非静态方法 使用对象实例来引用
        Dog dog1 = new Dog();
//        Function<Integer, Integer> function = dog1::eat;
//        UnaryOperator<Integer> function = dog1::eat;
//        System.out.println("剩余 " + function.apply(2) + "斤狗粮");
        IntUnaryOperator function = dog1::eat;
        System.out.println("剩余 " + function.applyAsInt(2) + "斤狗粮");
        
        // Dog::eat 使用类名来进行方法引用
        BiFunction<Dog, Integer, Integer> biFunction = Dog::eat;
        System.out.println("剩余了" + biFunction.apply(dog1, 5) + "斤狗粮");
        
        // 构造函数的方法引用
        Supplier<Dog> supplier = Dog::new;
        System.out.println("当前对象的名字为：" + supplier.get());
        
        // 带参数的构造函数的方法引用
        Function<String, Dog> function1 = Dog::new;
        System.out.println("有参数的方法引用：" + function1.apply("哈巴"));
    }
    
}

class Dog{

    public Dog() {
    }

    public Dog(String name) {
        this.name = name;
    }

    private String name = "哮天犬";
    private int food = 10;
    
    public static void bark(Dog dog){
        System.out.println(dog + "叫了");
    }
    
    public int eat(Dog this, int num){
        System.out.println("吃了 " + num + "斤狗粮");
        this.food -= num;
        return this.food;
    }

    @Override
    public String toString() {
        return this.name;
    }
    
    
}