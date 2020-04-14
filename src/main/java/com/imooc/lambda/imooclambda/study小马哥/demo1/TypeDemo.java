package com.imooc.lambda.imooclambda.study小马哥.demo1;

/**
 * 类型推断
 */
@FunctionalInterface
interface IMath{
    int add(int x, int y);
}

@FunctionalInterface
interface IMath2{
    int sub(int x, int y);
}

public class TypeDemo {

    public static void main(String[] args) {
        // 1、变量类型定义
        IMath lambda = (x, y) -> x + y;
        
        // 2、数组里
        IMath[] lambdas = {(x, y) -> x + y};
        
        // 3、强转
        Object lambda2 = (IMath)(x, y) -> x + y;
        
        // 4、通过返回类型
        IMath createLambda = createLambda();
        
        TypeDemo td = new TypeDemo();
        // 当有二义性的时候，使用强转对应的接口解决
        td.test((IMath) (x, y) -> x + y);
    }
    
    public void test(IMath math){
        
    }
    
    public void test(IMath2 math2){
        
    }
    
    private static IMath createLambda(){
        return (x, y) -> x + y;
    }
    
}
