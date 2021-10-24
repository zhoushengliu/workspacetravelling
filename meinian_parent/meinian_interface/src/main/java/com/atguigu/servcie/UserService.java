package com.atguigu.servcie;

import com.atguigu.pojo.User;

/**
 * UserService
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-08-02
 * @Description:
 */
public interface UserService {
    User findUserByUsername(String username);
}

