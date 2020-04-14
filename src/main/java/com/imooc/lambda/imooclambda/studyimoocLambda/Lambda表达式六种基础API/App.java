package com.imooc.lambda.imooclambda.studyimoocLambda.Lambda表达式六种基础API;

import java.util.UUID;
import java.util.function.*;

/**
 * 需求改动：
 * 所有的用户验证，可以同时获取用户的验证信息[是否认证成功 | 成功-返回用户 | 失败-null]
 */
public class App {

    public static void main(String[] args) {
//        IUserCredential ic = new UserCredentialImpl();
//        System.err.println(ic.verifyUser("admin"));
//
//        // TODO：默认方法
//        System.err.println(ic.getCredential("admin"));
//
//        String msg = "Hello world";
//
//        // TODO: 静态方法
//        if (IMessageFormat.verityMessage(msg)) {
//            IMessageFormat format = new MessageFormatImpl();
//            format.format(msg, "json");
//        }
//        
//        // TODO: 匿名内部类，实现接口的抽象方法
//        IUserCredential ic2 = new IUserCredential() {
//            @Override
//            public String verifyUser(String username) {
//                return "admin".equals(username) ? "管理员" : "会员";
//            }
//        };
//        System.err.println(ic2.verifyUser("manager"));
//        System.err.println(ic2.verifyUser("admin"));
//        
//        // TODO: Lambda表达式， 针对函数式接口的简单实现
//        IUserCredential ic3 = (String username) -> {
//            return "admin".equals(username) ? "lbd管理员" : "lbd会员";
//        };
//
//        System.err.println(ic3.verifyUser("manager"));
//        System.err.println(ic3.verifyUser("admin"));

        // TODO: java.util.function.Predicate
        Predicate<String> pre = (String username) -> {
            return "admin".equals(username);
        };

        System.err.println(pre.test("manager"));
        System.err.println(pre.test("admin"));
        
        // TODO: java.util.function.Consumer
        Consumer<String> con = (String message) -> {
            System.err.println("要发送的消息： " + message);
            System.err.println("消息发送完成");
        };
        
        con.accept("hello world");
        
        // TODO: java.util.function.Function
        Function<String, Integer> fun = (String gender) -> {
            return "male".equals(gender)?1:0;
        };

        System.err.println(fun.apply("male"));
        
        // TODO: java.util.function.Supplier
        Supplier<String> sup = () -> {
            return UUID.randomUUID().toString();
        };

        System.err.println(sup.get());
        System.err.println(sup.get());
        System.err.println(sup.get());
        
        // TODO: java.util.function.UnaryOperator (一元操作符)
        // TODO: 接收参数对象T，返回结果对象T
        UnaryOperator<String> uo = (String img) -> {
            img += "[100*200]";
            return img;
        };
        System.err.println(uo.apply("原图--"));
        
        // TODO: java.util.function.BinaryOperator (二元操作符)
        // TODO: 接收俩个参数T，返回一个T对象结果
        BinaryOperator<Integer> bo = (Integer i1, Integer i2) -> {
            return i1 > i2?i1:i2;
        };
        System.err.println(bo.apply(12, 12));
        
        // TODO: 总结，在java.util.function包中，提供了大量的函数式接口
        // TODO: Predicate       接收参数T对象，返回一个boolean类型结果
        // TODO: Consumer        接收参数T对象，没有返回值
        // TODO: Function        接收参数T对象，返回R对象
        // TODO：Supplier        不接受参数，直接通过get()获取指定类型的对象
        // TODO: UnaryOperator   接收参数T对象，执行业务处理后，返回更新后的T对象
        // TODO: BinaryOperator  接收俩个T对象，执行业务处理后，返回一个T对象
        
    }

}
