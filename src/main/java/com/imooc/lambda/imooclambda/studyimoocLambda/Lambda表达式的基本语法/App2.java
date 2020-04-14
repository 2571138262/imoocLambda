package com.imooc.lambda.imooclambda.studyimoocLambda.Lambda表达式的基本语法;

public class App2 {
    
    String s1 = "全局变量";
    
    // TODO 1、匿名内部类中对于变量的访问
    public void testInnerClass(){
        String s2 = "局部变量";
        
        new Thread(new Runnable() {
            String s3 = "内部变量";
            @Override
            public void run() {
                // TODO 1、访问全局变量
//                System.err.println(this.s1); // this.关键字 - 表示是当前匿名内部类型的对象
                System.err.println(s1);
                // TODO 2、局部变量访问， - 不能对局部变量进行数据的修改（在进行类型推导的时候它会认为当前局部变量的类型为final）
                System.err.println(s2);
                // TODO 3、内部变量访问
                System.err.println(this.s3);
            }
        }).start();
    }
    
    // TODO 2、Lambda表达式变量捕获
    public void testLambda(){
        String s2 = "局部变量Lambda";
        
        new Thread(() -> {
            String s3 = "内部变量Lambda";
            
            // TODO 访问全局变量 this.关键字，表示的就是所属方法所在类型的对象
            System.err.println(this.s1);
            // TODO 访问局部变量 - 不能对局部变量进行数据的修改（在进行类型推导的时候它会认为当前局部变量的类型为final）
            System.err.println(s2);
            // TODO 访问内部变量
            System.err.println(s2);
        }).start();
    }

    public static void main(String[] args) {
        App2 app = new App2();
//        app.testInnerClass();
        app.testLambda();
    }
    
}
