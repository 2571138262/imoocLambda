package com.imooc.lambda.imooclambda.studyimoocLambda.Lambda表达式在集合中的运用;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 方法引用
 *  1、静态方法引用的使用
 *      类型名称.方法名称() --> 类型名称::方法名称
 *  2、实例方法引用的使用
 *      创建类型对应的一个对象 --> 对象引用::实例方法名
 */
public class 方法引用Test {

    public static void main(String[] args) {
        // 存储Person对象的列表
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("tom", "男", 16));
        personList.add(new Person("jerry", "女", 15));
        personList.add(new Person("shuke", "男", 26));
        personList.add(new Person("beita", "女", 30));
        personList.add(new Person("damu", "男", 32));
        
        // TODO 1、匿名内部类实现方法     排序
        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        
        // TODO 2、lambda表达式的实现方式
        Collections.sort(personList, (p1, p2) -> p1.getAge() - p2.getAge());

        
        // TODO 3、静态方法引用
        Collections.sort(personList, Person::compareByAge);
        Collections.sort(personList, (p1, p2) -> Person.compareByAge(p1, p2));

        // TODO 4、实例方法引用
        PersonUtil pu = new PersonUtil();
        Collections.sort(personList, pu::compareByName);
        System.err.println("tom".hashCode());
        System.err.println("jerry".hashCode());

        System.err.println(personList);
        
        // TODO 5、构造方法引用 绑定函数式接口
        IPerson ip = Person::new;
        Person jerry = ip.initPerson("jerry", "女",22);
        System.err.println(jerry);


        


    }
    
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person{
    private String name;
    private String gender;
    private int age;

    public static int compareByAge(Person p1, Person p2){
        return p1.getAge() - p2.getAge();
    }
}

class PersonUtil{
    // 增加一个实例方法
    public int compareByName(Person p1, Person p2){
        return p1.getName().hashCode() - p2.getName().hashCode();
    }
}

interface IPerson{
    // 抽象方法，通过指定类型的构造方法初始化对象数据
    Person initPerson(String name, String gender, int age);
}