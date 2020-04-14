package com.imooc.lambda.imooclambda.studyimoocLambda.Lambda表达式在集合中的运用;

/**
 * 3、Stream API
 *      （1）、Stream聚合操作
 *      
 *      （2）、stream的处理流程
 *          数据源
 *          数据转换
 *          获取结果
 *      （3）、获取Stream对象
 *          1、从集合或者数组中获取
 *              Collection.stream(),如accounts.stream()
 *              Collection.parallelStream() 并行处理的 Stream对象
 *              Arrays.stream(T t)
 *          2、从BufferReader中获取Stream对象    
 *              BufferReader.lines() -> stream()
 *          3、静态工厂
 *              java.util.stream.IntStream.range()..
 *              java.nio.file.Files.walk()..
 *          4、自定构建
 *              java.util.Spliterator
 *          5、更多的方式
 *              Random.ints()
 *              Pattern.splitAsStream()..
 *      （4）、中间操作API（intermediate）
 *          操作结果是一个Stream对象，中间操作可以有一个或者多个连续的中间操作，
 *              需要注意的是，中间操作只记录操作方式，不做具体执行，直到结束操作发生时，才做数据的最终执行。
 *              中间操作 : 就是业务逻辑处理
 *          中间操作过程 - 无状态：数据处理时，不受前置中间操作的影响
 *                          map/filter/peek/parallel/sequential/unordered
 *                       - 有状态：数据处理时，受到前置中间操作的影响
 *                          distinct/sorted/limit/skip
 *      （5）、终结操作|结束操作（terminal）                    
 *          需要注意：一个Stream对象，只能有一个Terminal操作，这个操作一旦发生，就会真实处理数据，并且是不可逆的
 *          终止操作 - 非短路操作：当前的Stream对象必须处理完集合中的所有数据，才能得到处理结果
 *                      forEach/forEachOrdered/toArray/reduce/collect/min/max/count/iterator
 *                   - 短路操作：当前的Stream对象在处理过程中，一旦满足某个条件，就可以得到结尾
 *                      anyMatch/allMatch/noneMatch/findAny等
 *                      Short-circuiting, 无限大的Stream -> 有限大的Stream，
 */
public class StreamAPI {
    
    
    
}
