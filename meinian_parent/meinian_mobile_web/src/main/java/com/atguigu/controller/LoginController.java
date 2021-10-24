package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.constant.RedisMessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Member;
import com.atguigu.servcie.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * LoginController
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-08-02
 * @Description:
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private JedisPool jedisPool;

    @Reference
    private MemberService memberService;


    /**
     * 登录成功之后，需要进行会话管理
     * @param map
     * @return
     */
    @RequestMapping("/check")
    public Result check(@RequestBody Map map, HttpServletResponse response){
        // 获取用户输入的验证码
        String validateCode = (String) map.get("validateCode");
        // 获取用户的手机号码
        String telephone = (String) map.get("telephone");
        // 获取redis里面存入的真正的验证码
        String codeInRedis = jedisPool.getResource()
                .get(telephone + RedisMessageConstant.REDIS_LOGIN_RESOURCES);
        // 判断redis里面的验证码是否过期
        if (codeInRedis == null||!codeInRedis.equals(validateCode)){
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }else {
            // 验证码输入成功 如果成功，需要判断当前登录的是否是用户
            // 根据手机号码查询用户
           Member member =  memberService.findTelephone(telephone);
           if (member == null){
               // 说明不是会员，就需要进行注册
               member = new Member();
               member.setPhoneNumber(telephone);
               member.setRegTime(new Date());
               memberService.add(member);
           }
           // 存入到cookie,保存用户状态
            Cookie cookie = new Cookie("login_member_telephone",telephone);
           // 设置cookie存储的路径
            cookie.setPath("/");
            // 设置cookie的有效期为一个月
            cookie.setMaxAge(30*24*60*60);
            response.addCookie(cookie);
            return new Result(true,MessageConstant.LOGIN_SUCCESS);

        }

    }
}