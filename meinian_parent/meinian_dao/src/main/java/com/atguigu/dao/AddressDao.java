package com.atguigu.dao;

import com.atguigu.pojo.Address;

import java.util.List;

/**
 * AddressDao
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-08-03
 * @Description:
 */
public interface AddressDao  {
    List<Address> findAll();

}

