package com.imooc.lambda.imooclambda.函数式web框架RouterFunction模式.routers;

import com.imooc.lambda.imooclambda.函数式web框架RouterFunction模式.handlers.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 *  有点类似SpringMvc中Dispatcher干的事情， 类似于SpringMVC总入口
 */
@Configuration
public class AllRouters {
    
    @Bean
    RouterFunction<ServerResponse> userRouter(UserHandler handler){
        return RouterFunctions.nest(
                /**
                 * 相当于Controller类上边的 @RequestMapping("/user") 注意是类上边的
                 */
                RequestPredicates.path("/routerfunctionuser"),
                /**
                 * 下边相当于类里边的 @RequestMapping()   方法上
                 */
                // 得到所有用户
                RouterFunctions.route(RequestPredicates.GET("/"), handler::getAllUser)
                // 创建用户        
                .andRoute(RequestPredicates.POST("/").and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)), handler::createUser)
                // 根据Id删除用户
                .andRoute(RequestPredicates.DELETE("/{id}"), handler::deleteUserById)
        ); 
    }
    
}
