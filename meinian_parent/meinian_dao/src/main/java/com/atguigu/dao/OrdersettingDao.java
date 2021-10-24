package com.atguigu.dao;

import com.atguigu.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * OrdersettingDao
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-07-30
 * @Description:
 */
public interface OrdersettingDao {
    void add(OrderSetting data);

    long findOrderDate(Date orderDate);

    void editNumberByOrderDate(OrderSetting data);

    List<OrderSetting> getOrderSettingByMonth(Map map);

    OrderSetting findByOrderDate(Date date);

    void editReservationsByOrderDate(OrderSetting orderSetting);

}

