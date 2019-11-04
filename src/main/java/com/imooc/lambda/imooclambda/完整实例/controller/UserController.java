package com.imooc.lambda.imooclambda.完整实例.controller;


import com.imooc.lambda.imooclambda.完整实例.domain.User;
import com.imooc.lambda.imooclambda.完整实例.repository.UserRepository;
import com.imooc.lambda.imooclambda.完整实例.util.CheckUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * 以数组的形式一次返回数据
     *
     * @return
     */
    @GetMapping("/")
    public Flux<User> getAll() {
        return repository.findAll();
    }

    /**
     * 以SSE形式多次返回数据
     *
     * @return
     */
    @GetMapping(value = "/stream/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getStreamAll() {
        return repository.findAll();
    }

    @PostMapping(value = "/")
    public Mono<User> createUser(@Valid @RequestBody User user) {
        /**
         * Spring data JPA中新增和修改都是save, 有id的时候是修改， id为空的时候是新增
         *
         */
        // 根据实际情况是否置空id
        user.setId(null);
        CheckUtil.checkName(user.getName());
        return this.repository.save(user);
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable("id") String id) {
        /**
         * deleteById( )  方法没有返回值， 不能判断数据是否存在
         */
//        this.repository.deleteById(id);

        return this.repository.findById(id)
                /**
                 *  当你要操作数据， 并返回一个Mono 这个时候使用 flatMap( )
                 *  如果不操作数据， 只是转换数据， 使用 map( )
                 */
                .flatMap(user -> this.repository.delete(user)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * 修改数据
     * 存在的时候返回200 和修改后的数据， 不存在的时候返回404
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<User>> updateUser(
            @PathVariable("id") String id,
            @Valid @RequestBody User user){
        
        return this.repository.findById(id)
                /**
                 * flatMap 操作数据
                 */
                .flatMap(u -> {
                    u.setName(user.getName());
                    u.setAge(user.getAge());
                    return this.repository.save(u);
                })
                /**
                 * map: 转换数据
                 */
                .map(u -> new ResponseEntity<User>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * 根据Id查找用户
     * 存在返回用户信息，不存在返回404
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> findById(@PathVariable("id") String id){
        return this.repository.findById(id)
                /**
                 * 如果存在
                 */
                .map(u -> new ResponseEntity<User>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 根据年龄查询对应的用户列表
     * @param start
     * @param end
     * @return
     */
    @GetMapping("/age/{start}/{end}")
    public Flux<User> findByAge(@PathVariable("start") int start,
                                @PathVariable("end") int end){
        return this.repository.findByAgeBetween(start, end);
    }

    /**
     * 针对返回的结果是Flux的方法， 都要写俩个方法， 
     *          一个是以常规数组返回
     *          一个是以SSE流的形式返回
     * @param start
     * @param end
     * @return
     */
    @GetMapping(value = "/stream/age/{start}/{end}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamFindByAge(@PathVariable("start") int start,
                                @PathVariable("end") int end){
        return this.repository.findByAgeBetween(start, end);
    }


    /**
     * 查询年龄23-25之间的用户
     * @return
     */
    @GetMapping("/old")
    public Flux<User> oldUser(){
        return this.repository.oldUser();
    }

    /**
     * 针对返回的结果是Flux的方法， 都要写俩个方法， 
     *          一个是以常规数组返回
     *          一个是以SSE流的形式返回
     * @return
     */
    @GetMapping(value = "/stream/old", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamOldUser(){
        return this.repository.oldUser();
    }
}
