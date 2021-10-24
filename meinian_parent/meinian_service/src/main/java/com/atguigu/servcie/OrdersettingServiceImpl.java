package com.atguigu.servcie;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.OrdersettingDao;
import com.atguigu.pojo.OrderSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OrdersettingServiceImpl
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-07-30
 * @Description:
 */
@Service(interfaceClass = OrdersettingService.class)
@Transactional
public class OrdersettingServiceImpl implements OrdersettingService {

    @Autowired
    private OrdersettingDao ordersettingDao;

    @Override
    public void add(List<OrderSetting> datas) {
        for (OrderSetting data : datas) {
            // 根据日期进行查询，如果数据库里面已经有了日期，直接更新数据，如果没有数据，在添加数据
            long count =  ordersettingDao.findOrderDate(data.getOrderDate());
            if (count>0){
                ordersettingDao.editNumberByOrderDate(data);
            }else {
                ordersettingDao.add(data);
            }

        }

    }
    // this.leftobj = [
    //     { date: 1, number: 120, reservations: 1 },
    //     { date: 3, number: 120, reservations: 1 },
    //     { date: 4, number: 120, reservations: 120 },
    //     { date: 6, number: 120, reservations: 1 },
    //     { date: 8, number: 120, reservations: 1 }
    // ];
    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        // date: 2021-07-01 ---> 2021-07-31
        String dateBegin = date + "-01";
        String dateEnd = date + "-31";
        Map map = new HashMap<>();
        map.put("dateBegin",dateBegin);
        map.put("dateEnd",dateEnd);
        List<OrderSetting> lists =  ordersettingDao.getOrderSettingByMonth(map);
        List<Map> objects = new ArrayList<>();
        for (OrderSetting list : lists) {
            Map data = new HashMap<>();
            data.put("date",list.getOrderDate().getDate());
            data.put("number",list.getNumber());
            data.put("reservations",list.getReservations());
            objects.add(data);
        }

        return objects;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        long count =  ordersettingDao.findOrderDate(orderSetting.getOrderDate());
        if (count>0){
            ordersettingDao.editNumberByOrderDate(orderSetting);
        }else {
            ordersettingDao.add(orderSetting);
        }
    }
}