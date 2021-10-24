package com.atguigu.servcie;

import com.atguigu.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * OrdersettingService
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-07-30
 * @Description:
 */
public interface OrdersettingService {
    void add(List<OrderSetting> datas);

    List<Map> getOrderSettingByMonth(String date);

    void editNumberByDate(OrderSetting orderSetting);


}

