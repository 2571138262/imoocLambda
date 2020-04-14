package com.example.demo.handlers;

import com.example.demo.exceptions.CheckException;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;


/**
 * 由于默认里边含有多个异常处理的Handler
 * 所以我们要把我们的Handler的优先级调高， 不调高的话代码不会工作 @Order() 最小调到-2 值越小优先级越高
 */
@Order(-2)
@Component
public class ExceptionHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        ServerHttpResponse response = serverWebExchange.getResponse();
        // 设置响应头
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        // 设置返回类型
        response.getHeaders().setContentType(MediaType.TEXT_PLAIN);
        
        // 异常信息
        String errorMsg = toStr(throwable);

        DataBuffer wrap = response.bufferFactory().wrap(errorMsg.getBytes());

        return response.writeWith(Mono.just(wrap));
    }

    private String toStr(Throwable throwable) {
        // 已知异常
        if(throwable instanceof CheckException){
            CheckException e = (CheckException) throwable;
            return e.getFieldName() + ": invalid value " + e.getFieldValue();
        }
        // 未知异常, 需要打印堆栈，方便定位
        else{
            throwable.printStackTrace();
            return throwable.toString();
        }
    }
}
