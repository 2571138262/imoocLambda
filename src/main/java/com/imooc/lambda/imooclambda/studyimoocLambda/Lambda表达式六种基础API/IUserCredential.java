package com.imooc.lambda.imooclambda.studyimoocLambda.Lambda表达式六种基础API;

/**
 * 用户身份认证标记接口
 */
@FunctionalInterface
public interface IUserCredential {

    /**
     * 通过用户账号。验证用户身份接口
     * @param username 用户账号
     * @return 返回身份信息
     */
    String verifyUser(String username);
    
    default String getCredential(String username){
        // 模拟方法
        if ("admin".equals(username)){
            return "admin + 系统管理员用户";
        } else if ("manager".equals(username)){
            return "Manager + 用户管理员用户";
        } else {
            return "common + 普通会员用户";
        }
    }
    
}
