package com.atguigu.servcie;

import com.atguigu.entity.Result;

import java.util.Map;

/**
 * OrderService
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-07-31
 * @Description:
 */
public interface OrderService {
    Result submit(Map map);

    Map findById(Integer id);
}

