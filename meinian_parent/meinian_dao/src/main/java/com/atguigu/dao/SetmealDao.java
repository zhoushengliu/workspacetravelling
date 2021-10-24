package com.atguigu.dao;

import com.atguigu.pojo.Setmeal;
import com.github.pagehelper.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SetmealDao
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-07-28
 * @Description:
 */
public interface SetmealDao {
    void add(Setmeal setmeal);

    void setSetmealAndTravelgroup(HashMap map);

    Page<Setmeal> findPage(String queryString);

    List<Setmeal> findAll();

    Setmeal findById(Integer id);

    List<Map<String, Object>> findSetmealCount();

}

