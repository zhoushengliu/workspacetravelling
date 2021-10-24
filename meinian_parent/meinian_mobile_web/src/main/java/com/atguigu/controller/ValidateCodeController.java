package com.atguigu.controller;

import com.atguigu.constant.MessageConstant;
import com.atguigu.constant.RedisConstant;
import com.atguigu.constant.RedisMessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

/**
 * ValidateCodeController
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-07-31
 * @Description:
 */
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/send4Login")
    public Result send4Login(String telephone){
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        System.out.println("发送验证码:" + code);
        jedisPool.getResource().setex(telephone + RedisMessageConstant.REDIS_LOGIN_RESOURCES ,
                5*60,code + "" );
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }


    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){
        // 发送手机验证码
        // 生成四位数字的随机数
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        System.out.println("code====" +code);
        // 设置redis的过期时间
        jedisPool.getResource().setex(telephone ,5 * 60,code+"");
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}