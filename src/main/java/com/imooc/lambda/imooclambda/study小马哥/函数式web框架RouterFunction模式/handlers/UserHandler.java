package com.imooc.lambda.imooclambda.study小马哥.函数式web框架RouterFunction模式.handlers;

import com.imooc.lambda.imooclambda.study小马哥.函数式web框架RouterFunction模式.domain.User;
import com.imooc.lambda.imooclambda.study小马哥.函数式web框架RouterFunction模式.repository.UserRepositoryRouterFunction;
import com.imooc.lambda.imooclambda.study小马哥.函数式web框架RouterFunction模式.util.CheckUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {
    
    private final UserRepositoryRouterFunction userRepository;
    
    public UserHandler(UserRepositoryRouterFunction userRepository){
        this.userRepository = userRepository;
    }

    /**
     * 得到所有用户
     * @param request
     * @return
     */
    public Mono<ServerResponse> getAllUser(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(this.userRepository.findAll(), User.class);
    }

    /**
     * 创建用户
     * @param request
     * @return
     */
    public Mono<ServerResponse> createUser(ServerRequest request){

        // SpringBoot2.0.0是可以工作的， 但是2.0.1下面这个模式会报异常 会把当前线程阻塞
//        User user = request.bodyToMono(User.class).block();
        Mono<User> userMono = request.bodyToMono(User.class);

        return userMono.flatMap(u ->{
            // 校验代码 
            CheckUtil.checkName(u.getName());
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(this.userRepository.saveAll(userMono), User.class);
        });
    }

    /**
     * 根据Id删除用户
     * @param request
     * @return
     */
    public Mono<ServerResponse> deleteUserById(ServerRequest request){

        String id = request.pathVariable("id");
        
        return this.userRepository.findById(id).flatMap(user -> 
                this.userRepository.delete(user).then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
    
}
