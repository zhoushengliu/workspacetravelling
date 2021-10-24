package com.atguigu.dao;

import com.atguigu.pojo.Role;

import java.util.Set;

/**
 * RoleDao
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-08-02
 * @Description:
 */
public interface RoleDao {
    Set<Role> findRolesByUserId(Integer userId);
}

