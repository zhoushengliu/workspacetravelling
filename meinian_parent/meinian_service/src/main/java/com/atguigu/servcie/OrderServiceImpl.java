package com.atguigu.servcie;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.constant.MessageConstant;
import com.atguigu.dao.MemberDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrdersettingDao;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Member;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * OrderServiceImpl
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-07-31
 * @Description:
 */
@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersettingDao ordersettingDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private OrderDao orderDao;

//1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约
//
//2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
//
//3、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约则无法完成再次预约
//
//4、检查当前用户是否为会员，如果是会员则直接完成预约，如果不是会员则自动完成注册并进行预约
//
//5、预约成功，更新当日的已预约人数
    @Override
    public Result submit(Map map) {
//  1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约
        // 根据日期进行查询
        try {
            String orderDate = (String) map.get("orderDate");
            Date date = DateUtils.parseString2Date(orderDate);
            OrderSetting orderSetting =  ordersettingDao.findByOrderDate(date);
            // 判断是否为null
            if (orderSetting == null){
                return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
            }else {
                //2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
                // 获取已经预约的人数
                int reservations = orderSetting.getReservations();
                // 获取团的大小
                int number = orderSetting.getNumber();
                if (reservations >= number){
                    return new Result(false,MessageConstant.ORDER_FULL);
                }
                //4、检查当前用户是否为会员，如果是会员则直接完成预约，如果不是会员则自动完成注册并进行预约
                // 获取用户手机号码
                String telephone = (String) map.get("telephone");
                // 根据手机号码进行查询
                Member member = memberDao.findByTelephone(telephone);
                if (member!=null){
                    // 是会员，直接下单   同一天日期， 同一个套餐id 同一个用户id
                    //3、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），
                    // 如果是重复预约则无法完成再次预约
                    // 获取套餐id
                    Integer setmealId = Integer.parseInt((String) map.get("setmealId")) ;
                    // 获取会员id
                    Integer memberId = member.getId();
                    Order order = new Order(memberId,date,null,null,setmealId);
                    List<Order> lists =  orderDao.findByCondition(order);
                    if (lists!=null && lists.size()>0){
                        return new Result(false,MessageConstant.HAS_ORDERED);
                    }

                }else {
                    // 不是会员，需要进行注册成会员
                    member = new Member();
                    member.setName((String) map.get("name"));
                    member.setSex((String) map.get("sex"));
                    member.setIdCard((String) map.get("idCard"));
                    member.setPhoneNumber((String) map.get("telephone"));
                    member.setRegTime(new Date());
                    memberDao.add(member);
                }
                //5、更新当日的已预约人数
                orderSetting.setReservations(orderSetting.getReservations()+1);
                ordersettingDao.editReservationsByOrderDate(orderSetting);
                // 下订单
                Order order = new Order();
                order.setMemberId(member.getId());
                order.setOrderDate(date);
                order.setOrderType((String) map.get("orderType"));
                order.setOrderStatus(Order.ORDERSTATUS_NO);
                order.setSetmealId(Integer.parseInt((String) map.get("setmealId")));
                orderDao.add(order);
                return new Result(true,MessageConstant.ORDER_SUCCESS,order);
            }


        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true,MessageConstant.ORDER_FULL);
        }


    }

    @Override
    public Map findById(Integer id) {
        Map map =  orderDao.findById(id);
        return map;
    }
}