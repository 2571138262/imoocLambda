package com.imooc.lambda.imooclambda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class ImooclambdaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImooclambdaApplication.class, args);
    }

}
