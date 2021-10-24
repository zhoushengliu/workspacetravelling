package com.atguigu.controller;

import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import org.apache.poi.hpsf.ReadingNotSupportedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-08-03
 * @Description:
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @RequestMapping("/getUsername")
    public Result getUsername(){
        // 从springsecurity的上下文里面获取用户的名称
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,user.getUsername());
    }
}