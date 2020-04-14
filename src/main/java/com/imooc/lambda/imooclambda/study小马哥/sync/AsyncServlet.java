package com.imooc.lambda.imooclambda.study小马哥.sync;

import java.util.concurrent.CompletableFuture;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@WebServlet(name = "AsyncServlet")
public class AsyncServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long l = System.currentTimeMillis();

        // 开启异步
        AsyncContext asyncContext = request.startAsync();

        // 执行业务代码 通过线程池执行 JDK8 
        CompletableFuture.runAsync(() -> {
            doSomeThing(asyncContext, asyncContext.getRequest(), asyncContext.getResponse());
        });


        long l1 = System.currentTimeMillis();

        System.out.println("Sync use:" + (l1 - l));
    }

    private void doSomeThing(AsyncContext asyncContext, ServletRequest request, ServletResponse response){
        // 模拟耗时操作
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 
        try {
            response.getWriter().append("done");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 业务代码处理完毕， 通知结束
        asyncContext.complete();
        
    }
}
