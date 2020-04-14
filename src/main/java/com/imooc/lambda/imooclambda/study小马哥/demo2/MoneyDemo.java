package com.imooc.lambda.imooclambda.study小马哥.demo2;

import java.text.DecimalFormat;
import java.util.function.Function;


class MyMoney{
    private final int money;

    public MyMoney(int money) {
        this.money = money;
    }
    
    public void printMoney(Function<Integer, String> iMoneyFormat){
        System.out.println("我的存款：" + iMoneyFormat.apply(this.money));
    }
}

public class MoneyDemo {

    public static void main(String[] args) {
        MyMoney m = new MyMoney(99999);
        Function<Integer, String> integerStringFunction = i -> new DecimalFormat("#,###").format(i);
        // 函数接口链式操作
        m.printMoney(integerStringFunction.andThen(s -> "人民币" + s));
        m.printMoney(integerStringFunction.compose(s -> 1 + s));
    }
    
}
