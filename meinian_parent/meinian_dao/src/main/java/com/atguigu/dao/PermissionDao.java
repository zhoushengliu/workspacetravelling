package com.atguigu.dao;

import com.atguigu.pojo.Permission;

import java.util.Set;

/**
 * PermissionDao
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-08-02
 * @Description:
 */
public interface PermissionDao {
    Set<Permission> findPermissionsByRoleId(Integer roleId);
}

