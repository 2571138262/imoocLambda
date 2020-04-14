package com.imooc.lambda.imooclambda.studyimoocLambda;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // TODO: 传统模式下，新线程的创建
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println("threading... " + Thread.currentThread().getName());
            }
        }).start();
        
        // TODO: JDK8 新特性， lambda表达优化线程模型
        new Thread(() -> {
            System.err.println("Lambda threading... " + Thread.currentThread().getName());
        }).start();
        
    }
    
}
