package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Order;
import com.atguigu.servcie.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * OrderMobileController
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-07-31
 * @Description:
 */
@RestController
@RequestMapping("/order")
public class OrderMobileController {

    @Autowired
    private JedisPool jedisPool;

    @Reference
    private OrderService orderService;

    @RequestMapping("/findById")
    public Result findById(Integer id){
      Map map =  orderService.findById(id);
      return new Result(true,MessageConstant.ORDER_SUCCESS,map);
    }



    @RequestMapping("/submit")
    public Result submit(@RequestBody Map map){
        // 获取用户输入的手机号码
        String telephone = (String) map.get("telephone");
        // 校验用户输入的验证码
        String code = (String) map.get("validateCode");
        // 获取真正的验证码
        String codeInRedis = jedisPool.getResource().get(telephone);
        // 判断用户输入的验证码是否一致
        if (codeInRedis == null || !code.equals(codeInRedis)){
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        // 允许下订单
        // 设置移动端预约
        map.put("orderType", Order.ORDERTYPE_WEIXIN);
        Result result =  orderService.submit(map);
        return result;
    }
}