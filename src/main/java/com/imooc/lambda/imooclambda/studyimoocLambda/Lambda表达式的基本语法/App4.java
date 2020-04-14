package com.imooc.lambda.imooclambda.studyimoocLambda.Lambda表达式的基本语法;

/**
 * 方法重载对Lambda表达式的影响
 */
public class App4 {
    
    interface Pararm1{
        void outInfo(String info);
    }
    
    interface Pararm2{
        void outInfo(String info);
    }
    
    // 定义重载方法
    public void lambdaMethod(Pararm1 param){
        param.outInfo("Hello param1 imooc!");
    }
    
    public void lambdaMethod(Pararm2 param){
        param.outInfo("Hello param2 Imooc!");
    }

    public static void main(String[] args) {
        App4 app = new App4();
        app.lambdaMethod(new Pararm1() {
            @Override
            public void outInfo(String info) {
                System.err.println(info);
            }
        });
        app.lambdaMethod(new Pararm2() {
            @Override
            public void outInfo(String info) {
                System.err.println("---------------");
                System.err.println(info);
            }
        });
        
        // TODO Lambda表达式在类型检查 -> 自动推导lambda表达式的目标类型， 
        /**
         * lambdaMethod() -> 方法 -> 重载方法
         *          --> Param1 函数式接口
         *          --> Param2 函数式接口
         *          调用方法 -> 传递Lambda表达式 -> 自动推导 -> 
         *          -> Pararm1 | Pararm2
         */
//        app.lambdaMethod((String info) -> {
//            System.err.println(info);
//        });
    }
    
}
