package com.imooc.lambda.imooclambda.WebFlux开发;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class ReactorDemo {

    public static void main(String[] args) {
        /**
         *  reactor = jdk8 stream + jdk9 reactive stream
         *  Mono 表示 0 - 1 个元素
         *  Flux 表示 0 - N 个元素
         */
        
        String[] strs = {"1", "2", "3"};

        /**
         * 定义订阅者
         */
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                
            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        };
        
        // 这里就是jdk8的 Stream 操作
        Flux.fromArray(strs).map(s -> Integer.parseInt(s))
        /**
         * 最终操作
         * 这里就是 jdk9 的reactive stream
         */
        .subscribe(subscriber);
    }

}
