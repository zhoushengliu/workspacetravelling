package com.atguigu.dao;

import com.atguigu.pojo.User;

/**
 * UserDao
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-08-02
 * @Description:
 */
public interface UserDao {
    User findUserByUsername(String username);
}

