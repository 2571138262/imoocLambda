# Lambda表达式

## 第一章：Java为什么引入Lambda表达式
### 1、什么是Lambda
* Lambda 表达式也被称为箭头函数、匿名函数、闭包
* Lambda 表达式体现的是轻量级函数式编程思想
* '->' 符号是 Lambda 表达式核心操作符号，符号左侧是操作参数，符号右侧是操作表达式
* JDK8 新特性

### 2、Model Code ad Data 
* Model Code as Data, 编码及数据，尽可能轻量级的将代码封装为数据
* 解决方案：接口 & 实现类 (匿名内部类)
* 存在问题：语法冗余、this关键字、变量捕获、数据控制等
###### 在匿名内部类中，this关键字在内部类型中，他的变量绑定，以及变量访问，存在很大的误区
###### 在内部类型中，对于当前作用域变量的处理，会有一些特殊的处理，

### 3、项目问题：功能接口的设计及优化
* 需求环境：线程类的创建
* 解决方案：匿名内部类实现
* 解决方案：lambda表达式实现

### 4、为什么要使用Lambda表达式呢？
* 它不是解决未知问题的新技术
* 对现有解决方案的语义化优化
* 需要根据实际需求考虑性能问题


## 第二章、Lambda表达式基础知识
### 1、函数式接口（Function Interface）
* 函数式接口，就是Java类型系统中的接口
* 函数式接口，是只包含一个接口方法的特殊接口
* 语义化检查注解：@FunctionalInterface
* 默认接口方法
###### 默认方法应用的场景是给所有的实现当前接口的子类的对象，增加的一些通用的方法
* 静态接口方法
###### 静态方法
* 来自 Object 继承来的方法

### 2、函数式接口（Function Interface）
* 函数式接口，只包含一个操作方法
* Lambda表达式，只能操作一个方法
* Java中的Lambda表达式，核心就是一个函数式接口的实现
### 3、Java类型系统内建函数式接口
* java.lang.Runnable
* java.lang.Comparable
* java.lang.Comparator
* java.io.FileFilter


### 4、jdk8提供了java.util,function包，提供了常用的函数式功能接口
* java.util.function.Predicate<T>
###### 接收参数对象 T， 返回一个boolean类型结果
* java.util.function.Consumer<T>
###### 接收参数对象T，不返回结果
* java.util.function.Function
###### 接收参数对象T，返回结果对象R
* java.util.function.Supplier
###### 不接受参数，提供T对象的创建工厂
* java.util.function.UnaryOperator (一元运算符)
###### 接收参数对象T，返回结果对象T
* java.util.function.BinaryOperator (二元运算符)
###### 接收两个T对象，返回一个T对象结果


### 5、Lambda表达式基本语法
####（1）、Lambda表达式基本语法
* 声明：就是和lambda表达式绑定的接口类型
* 参数：包含在一堆圆括号中，和绑定的接口中的抽象方法中的参数个数及顺序一致
* 操作符：-> 
* 执行代码块：包含在一对大括号中，出现在操作符号的右侧
###### [接口声明] = (参数) -> {执行代码块};
####（2）、带参数的Lambda表达式
* Lambda表达式的参数，可以附带0个到n个参数，括号中的参数类型可以不用指定，
jvm在运行时，会自动根据绑定的抽象方法中的参数进行推导
####（3）、带返回值的Lambda表达式
* Lambda表达式的返回值，如果代码块只有一行，并且没有大括号，不用谢return关键字，
单行代码的执行结果，会自动返回，如果添加了大括号，并且有多行代码，必须通过return关键字来返回

### 6、Lambda表达式变量捕获 
###### 所谓变量捕获，指定是表达式在使用过程中，对于所属作用域范围变量的访问规则
* 匿名内部类中的变量捕获
* Lambda表达式中的变量捕获


### 7、Lambda表达式类型检查
* 表达式类型检查
###### (x, y)->{...} ---> test(MyInterface) ---> lambda表达式 -> MyInterface类型，这个就是对于Lambda表达式的类型检查，MyInterface接口就是表达式的目标类型(target typing)
* 参数类型检查
###### (x, y)->{...} ---> MyInterface.strategy(T t, R r) ---> MyInterface<String, List> inter ---> T==String, R==List ---> lambda ---> (x, y) == strategy(T t, R r) ---> x==T==String y==R==List Lambda表达式参数的类型检查

### 8、方法重载的问题
* Java类型系统中的方法重载
* 方法重载的实现
* 当方法重载遇上Lambda表达式

### 4、深入理解lambda表达式
* Lambda表达式底层解析运行原理
* Lambda表达式在JVM底层解析成私有静态方法和匿名内部类型、
* 通过实现接口的匿名内部类型中接口方法调用静态实现方法，完成Lambda表达式的执行


## 第三章、Lambda表达式在集合中的运用
### 1、方法引用
* 方法引用是结合Lambda表达式的一种语法特性 
* 静态方法引用
* 实例方法引用
* 构造方法引用

### 2、Stream 概述
#### （1）、什么是Stream
    并不是IO操作中的IO流Stream，这里的Stream是和数据算法和运算有关的

### 3、Stream API
#### （1）、Stream聚合操作
* stream的处理流程


    数据源
    数据转换
    获取结果
* 获取Stream对象

    
    1、从集合或者数组中获取
        Collection.stream(),如accounts.stream()
        Collection.parallelStream() 并行处理的 Stream对象
        Arrays.stream(T t)
    2、从BufferReader中获取Stream对象    
        BufferReader.lines() -> stream()
    3、静态工厂
        java.util.stream.IntStream.range()..
        java.nio.file.Files.walk()..
    4、自定构建
        java.util.Spliterator
    5、更多的方式
        Random.ints()
        Pattern.splitAsStream()..    
#### （2）、API : intermediate 中间/记录操作【无状态|有状态】
* 操作结果是一个Stream对象，中间操作可以有一个或者多个连续的中间操作，
      需要注意的是，中间操作只记录操作方式，不做具体执行，直到结束操作发生时，才做数据的最终执行。
* 中间操作 : 就是业务逻辑处理
* 中间操作过程 - 无状态：数据处理时，不受前置中间操作的影响
                  map/filter/peek/parallel/sequential/unordered
               - 有状态：数据处理时，受到前置中间操作的影响
                  distinct/sorted/limit/skip
#### （3）、API : terminal终结/终止操作【非短路|短路】
* 需要注意：一个Stream对象，只能有一个Terminal操作，这个操作一旦发生，就会真实处理数据，并且是不可逆的
* 终止操作 - 非短路操作：当前的Stream对象必须处理完集合中的所有数据，才能得到处理结果
              forEach/forEachOrdered/toArray/reduce/collect/min/max/count/iterator
           - 短路操作：当前的Stream对象在处理过程中，一旦满足某个条件，就可以得到结尾
              anyMatch/allMatch/noneMatch/findAny等
              Short-circuiting, 无限大的Stream -> 有限大的Stream，
              
### 4、Stream操作集合中的数据  
* 类型转换：其他类型 (创建/获取) -> Stream对象
* 类型转换： Stream对象 -> 其他类型
* Stream常见API操作


## 第四章、Lambda表达式在实际生产中的应用
### 1、Lambda和Stream性能问题
### 2、Stream并行运行原理

