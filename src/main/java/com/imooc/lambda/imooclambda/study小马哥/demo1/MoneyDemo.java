package com.imooc.lambda.imooclambda.study小马哥.demo1;

import java.text.DecimalFormat;
import java.util.function.Function;

/**
 * 接口  可以省略
 */
//interface IMoneyFormat{
//    String format(int i);
//}

class MyMoney{
    private final int money;
    
    public MyMoney(int money){
        this.money = money;
    }
    
    public void printMonry(Function<Integer, String> imf){
        System.out.println("我的存款： " + imf.apply(this.money));
    }
}   

public class MoneyDemo {

    public static void main(String[] args) {
        MyMoney mm = new MyMoney(99999999);

        Function<Integer, String> integerStringFunction = i -> new DecimalFormat("#, ###").format(i);
        mm.printMonry(i -> new DecimalFormat("#, ###").format(i));

        // 函数接口链式操作
        Function<Integer, String> integerStringFunction1 = i -> "人民币" + new DecimalFormat("#, ###").format(i);
        mm.printMonry(integerStringFunction1);
    }
    
}
