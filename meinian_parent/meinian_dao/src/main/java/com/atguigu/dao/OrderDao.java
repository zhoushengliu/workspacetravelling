package com.atguigu.dao;

import com.atguigu.pojo.Order;

import java.util.List;
import java.util.Map;

/**
 * OrderDao
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-07-31
 * @Description:
 */
public interface OrderDao {
    List<Order> findByCondition(Order order);

    void add(Order order);

    Map findById(Integer id);

    int getTodayOrderNumber(String today);

    int getTodayVisitsNumber(String today);

    int getThisWeekAndMonthOrderNumber(Map<String, Object> paramWeek);

    int getThisWeekAndMonthVisitsNumber(Map<String, Object> paramWeekVisit);

    List<Map<String,Object>> findHotSetmeal();


}

