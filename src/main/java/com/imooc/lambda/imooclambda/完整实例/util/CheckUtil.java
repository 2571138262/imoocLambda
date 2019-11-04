package com.imooc.lambda.imooclambda.完整实例.util;

import com.imooc.lambda.imooclambda.完整实例.exceptions.CheckException;

import java.util.stream.Stream;

public class CheckUtil {

    private final static String[] INVALID_NAMES = {"admin", "guanliyuan"};
    

    /**
     * 校验名字，不成功的时候抛出校验异常
     * @param value
     */
    public static void checkName(String value) {

        Stream.of(INVALID_NAMES).filter(name -> name.equalsIgnoreCase(value))
                .findAny().ifPresent(name -> {
                    throw new CheckException("name", value);
        });
    }
}
