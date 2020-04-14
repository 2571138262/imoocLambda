package com.imooc.lambda.imooclambda.studyimoocLambda.Lambda表达式在集合中的运用;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stream流概述
 */
public class Stream流Test {

    public static void main(String[] args) {
        // 1、添加测试数据， 存储多个账号列表
        List<String> accounts = new ArrayList<>();
        accounts.add("tom");
        accounts.add("jerry");
        accounts.add("beita");
        accounts.add("shuke");
        accounts.add("damu");
        
        
        // TODO 1.1、业务需求，长度大于等于5的才是有效账号
        for (String account : accounts) {
            if (account.length() >= 5){
                System.err.println("有效账号：" + account);
            }
        }
        
        // TODO 1.2、迭代方式进行操作
        Iterator<String> it = accounts.iterator();
        while (it.hasNext()){
            String account = it.next();
            if (account.length() >= 5){
                System.err.println("it有效账号：" + account);
            }
        }
        
        // TODO 1.3、Stream结合Lambda表达式，完成业务处理
        List validAccounts = accounts.stream().filter(s -> s.length() >= 5).collect(Collectors.toList());
        System.err.println(validAccounts);
        
    }
    
}
