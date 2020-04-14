package com.imooc.lambda.imooclambda.studyimoocLambda.Lambda表达式六种基础API;

public class MessageFormatImpl implements IMessageFormat {

    @Override
    public String format(String message, String format) {
        System.err.println("消息转化...");
        return message;
    }
}
