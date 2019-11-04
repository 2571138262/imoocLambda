package com.imooc.lambda.imooclambda.sync;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@WebServlet(name = "SyncServlet")
public class SyncServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long l = System.currentTimeMillis();

        // 执行业务代码
        try {
            doSomeThing(request, response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long l1 = System.currentTimeMillis();

        System.out.println("Sync use:" + (l1 - l));
    }

    private void doSomeThing(HttpServletRequest request, HttpServletResponse response) throws InterruptedException, IOException {
        // 模拟耗时操作
        TimeUnit.SECONDS.sleep(5);
        
        // 
        response.getWriter().append("done");
        
    }
}
