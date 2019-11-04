package com.imooc.lambda.imooclambda.函数式web框架RouterFunction模式.repository;

import com.imooc.lambda.imooclambda.函数式web框架RouterFunction模式.domain.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepositoryRouterFunction extends ReactiveMongoRepository<User, String> {

    /**
     * 根据年龄查找用户
     * @param start
     * @param end
     * @return
     */
    Flux<User> findByAgeBetween(int start, int end);


    @Query("{'age':{'$gte':23, '$lte':25}}")
    Flux<User> oldUser();
    
}
