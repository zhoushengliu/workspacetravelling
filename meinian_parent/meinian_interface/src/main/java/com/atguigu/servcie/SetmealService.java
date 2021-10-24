package com.atguigu.servcie;

import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * SetmealService
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-07-28
 * @Description:
 */
public interface SetmealService {
    void add(Setmeal setmeal, Integer[] travelgroupIds);

    PageResult findPage(QueryPageBean queryPageBean);

    List<Setmeal> findAll();


    Setmeal findById(Integer id);

    List<Map<String, Object>> findSetmealCount();


}

