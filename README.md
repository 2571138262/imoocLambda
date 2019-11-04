### Stream流编程
##### 概念：
###### Stream是一个高级的迭代器，它不是一个集合，不是一个数据结构，不会存放数据，它关注的是怎么把数据高效的处理，就是把数据在一个流水线中处理的过程
###### 外部迭代和内部迭代
##### 惰性求值
###### 中间操作： 中间操作就是返回Stream流的操作
    无状态操作 - 函数接口是一个参数的
    有状态操作 - 函数接口中有俩个参数的就是有状态的
###### 终止操作 
    短路操作
######  parallel / sequential 这俩个操作也是中间操作（也是返回的Stream流），但是他们不创建流，他们只修改 Head的并行标志
##### 流的创建
###### 
        //从集合创建
        list.stream();
        list.parallelStream();// 并行流
###### 
        // 从数组创建
        Arrays.stream(new int[]{2,3,5});
######         
        // 创建一个数字流
        IntStream.of(1, 2, 3);
        IntStream.rangeClosed(1, 10); // 创建一个带1 - 10 的流
######         
        // 使用Random创建一个无限流
        new Random().ints().limit(10);
######         
        // 自己创建流  这里用随机来创建的流进行指定
        Random random = new Random();
        Stream.generate(() -> random.nextInt()).limit(20);
        
### Stream流编程 - 中间操作
##### 无状态操作：（当前的操作跟其他元素的前后没有依赖关系）
        map / mapToXxx
    flatMap / flatMapToXxx  flatMap A -> B属性（是个集合），最终得到所有A元素里面的所有B属性的集合 
        注： intStream/longStream 并不是Stream的子类，所有需要进行装箱boxed
    filter
    peek 用于debug， 是个中间操作， forEach是个终止操作
    unordered
    
##### 有状态操作： （结果需要依赖其他的元素）    
    distinct
    sorted
    limit / skip limit 用于无限流 
    
### Stream 流编程  -  终止操作
##### 非短路操作：
    forEach / forEachOrdered  parallel并行流中使用forEachOrdered保证并行流顺序
    collect / toArray  收集， 转成一个数组或者是一个list集合
    reduce  这个方法是将多行数据转换成一行数据
    min / max / count
    
##### 短路操作：   
    findFirst / findAny
    allMatch / anyMatch / noneMatch
    
### 并行流
##### 调用parallel产生一个并行流
##### 多次调用parallel / sequential,以最后一次为准
##### 并行流使用的线程池：ForkJoinPool.commonPool
###### 默认的线程数是 ： 当前机器的cpu个数
##### 使用这个属性可以修改默认的线程数          
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");

##### 使用自己的线程池， 不使用默认线程池，防止任务被阻塞
        ForkJoinPool pool = new ForkJoinPool(20);
        pool.submit(() -> IntStream.range(1, 100).parallel().peek(StreamDemo5并行流::debug).count());
        pool.shutdown();
        
### 收集器
        // 测试数据
        List<Student> students = Arrays.asList(
                new Student("小明", 10, Gender.MALE, Grade.ONE),
                new Student("大明", 9, Gender.MALE, Grade.THREE),
                new Student("大白", 8, Gender.FEMALE, Grade.TWO),
                new Student("小黑", 13, Gender.FEMALE, Grade.FOUR),
                new Student("小红", 7, Gender.FEMALE, Grade.THREE),
                new Student("小黄", 13, Gender.MALE, Grade.ONE),
                new Student("小青", 13, Gender.FEMALE, Grade.THREE),
                new Student("小紫", 9, Gender.FEMALE, Grade.TWO),
                new Student("小王", 6, Gender.MALE, Grade.ONE),
                new Student("小李", 6, Gender.MALE, Grade.ONE),
                new Student("小马", 14, Gender.FEMALE, Grade.FOUR),
                new Student("小刘", 13, Gender.MALE, Grade.FOUR)       
        );
        
        
        // 得到所有学生的年龄列表
        // student -> student.getAge()  -->  不会多生成一个类似 lambda$0这样的函数
        Set<Integer> ages = students.stream().map(Student::getAge).collect(Collectors.toSet());
        System.out.println("所有学生的年龄：" + ages);
        // 调用Collectors.toCollection() 自己传一个集合的提供者TreeSet::new
        Set<Integer> ages2 = students.stream().map(Student::getAge).collect(Collectors.toCollection(TreeSet::new));
        
        // 统计汇总信息
        IntSummaryStatistics collect = students.stream().collect(Collectors.summarizingInt(Student::getAge));
        System.out.println("年龄汇总信息为 : " + collect);
        
        // 分块
        Map<Boolean, List<Student>> genders = students.stream().collect(Collectors.partitioningBy(s -> s.getGender() == Gender.MALE));
        // System.out.println("男女学生列表 ： " + genders);

        // 分组
        Map<Integer, List<Student>> grades = students.stream().collect(Collectors.groupingBy(Student::getGrade));
        //System.out.println("学生班级列表" + grades);
        
        // 得到所有班级学生的个数
        Map<Integer, Long> gradeConut = students.stream().collect(Collectors.groupingBy(Student::getGrade, Collectors.counting()));
        System.out.println("每个班级学生个数列表" + gradeConut);
        
### 验证Stream运行机制、
##### 链式， Head -> nextStage
##### 并行 fork / join 使用同一个线程池 （阻塞）
        import java.util.Random;
        import java.util.stream.Stream;

        /**
         * 验证stream运行机制
         * 
         *  1、所有操作是链式调用，一个元素只迭代一次
         *  2、每一个中间操作返回一个新的流，流里面有一个属性，sourceStage执行同一个地方，就是链表的头(Head)
         *  3、Head -> nextStage -> nextStage -> ... -> null 链式调用的过程
         *  4、有状态操作会把无状态操作截断， 单独处理
         *  5、并行环境下，有状态的中间操作不一定能并行操作
         *  
         *  6、 parallel / sequetial 这俩个操作也是中间操作（也是返回的Stream流），但是他们不创建流，他们只修改 Head的并行标志
         */
        public class StreamDemo7运行机制 {
        
            public static void main(String[] args) {
                Random random = new Random();
                // 随机产生数据
                Stream<Integer> stream = Stream.generate(() -> random.nextInt())
                        // 产生500个（无限流需要短路操作）
                        .limit(500)
                        // 第1个无状态操作
                        .peek(s -> System.out.println("peek: " + s))
                        // 第2个无状态操作
                        .filter(s -> {
                            System.out.println("filter: " + s);
                            return (int) s > 1000000;
                        })
                        // 有状态操作
                        .sorted((i1, i2) ->{
                            System.out.println("排序： " + i1 + ", " + i2 );
                            return i1.compareTo(i2);
                        })
                        // 又一个无状态操作
                        .peek(s -> {
                            System.out.println("peek2: " + s);
                        })
                        .parallel();
                // 终止操作
                stream.count();
                
            }
            
        }
        



# Reactive Stream
### 概念 
##### JDK9引入的一套标准，是一套基于发布订阅者模式的数据处理的规范(机制)
### 背压 (back press)
##### 背压说白了就是一个交互， 一个反馈，就是发布者和订阅者之间的一个互动


# Spring WebFlux 
### 概念
##### 它是Spring5 提出的一种新的开发Web的技术栈，是一种非阻塞的开发模式，运行在Netty 或者是 Servlet3.1的容器上，可以支持非常高的并发量
### 和Spring MVC的关系
##### WebFlux：
###### 1、是一种非阻塞的技术栈， （可以在一个线程里边处理更加多的请求）
###### 2、基于Reactive Streams， 可以运行在Servlet 3.1之后容器上（支持异步Servlet的容器），或者运行在Netty上边（Spring 默认的容器就是Netty）
###### 3、新的WebFulx模式里边， 关系型数据库目前都是不支持响应式编程的，（Mysql、Oracle，基于JDBC的数据库）
    
##### 传统的mvc：
###### 1、是一种同步的阻塞IO开发模式，（一个请求对应容器里的一个线程）
###### 2、基于Servlet API 所以必须运行在Servlet容器上边
###### 3、支持关系型数据库

### 优势
###### 支持非常高的并发量

### SSE (Server-Sent Events)
##### flux返回多次数据， 但是Http协议是一问一答的形式，所以WebFlux是如何做到多次返回呢？（依赖H5的 SSE）

### 完整实例(采用mongodb数据库)
##### 1、依赖

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-mongodb-reactive</artifactId>
        </dependency>
##### 2、@EnableReactiveMongoRepositories 
##### 3、定义对象
        @Document(collection = "user")
        @Data
##### 4、定义对应的仓库

        @Repository
        public interface UserRepository extends ReactiveMongoRepository<User, String> {
        }
##### 5、启动mongoDB
       sc.exe create MongoDB binPath= "\"D:\Mongodb\bin\mongod.exe\" --service --config=\"D:\Mongodb\mongod.cfg\"" DisplayName= "MongoDB" start= "auto"
       默认端口是27017
##### 6、数据库配置
        spring.data.mongodb.uri=mongodb://localhost:27017/webflux
        
### RouterFunction 模式
##### WebFlux 可以运行在老的Servlet3.1容器上，或者是netty容器上， 所以需要将俩个容器的不同点抽象出来：
    ServletRequest  <---> HttpServletRequest
    ServletResponse <---> HttpServletResponse
##### 开发过程：
    HandlerFunction (输入 ServerRequest 返回 ServletResponse)
    -> RouterFunction (请求URL 和 HandlerFunction 对应起来)
    -> HttpHandler
    -> Server处理 
    
### 在处理方法中注意:
#### 我们在使用响应式编程的时候 Mono<T> Flux<T> 实际上是个流，它是一个发布者，我们任何时候都不能调这个发布者的订阅方法(就是我们自己的代码里边不能消费它,这个消费必须交给Spring框架来消费) 
