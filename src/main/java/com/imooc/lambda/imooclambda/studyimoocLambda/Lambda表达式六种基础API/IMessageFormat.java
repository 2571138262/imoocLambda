package com.imooc.lambda.imooclambda.studyimoocLambda.Lambda表达式六种基础API;

/**
 * 消息传输格式化转换接口
 */
@FunctionalInterface
public interface IMessageFormat {

    /**
     * 消息转换的接口
     * @param message 要转换的消息
     * @param format 转换的格式
     * @return 
     */
    String format(String message, String format);

    /**
     * 消息合法性验证方法
     * @param msg
     * @return
     */
    static boolean verityMessage(String msg){
        if (msg != null){
            return true;
        }
        return false;
    }
    
    String toString();
    
}
