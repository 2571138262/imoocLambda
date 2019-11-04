package com.imooc.lambda.imooclambda.完整实例.repository;

import com.imooc.lambda.imooclambda.完整实例.domain.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

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
