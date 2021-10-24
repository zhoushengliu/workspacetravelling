package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Setmeal;
import com.atguigu.servcie.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * SetmealMobileController
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-07-31
 * @Description:
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealMobileController {

    @Reference
    private SetmealService setmealService;

    @RequestMapping("/findById")
    public Result findById(Integer id){
       Setmeal setmeal =  setmealService.findById(id);
       return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
    }

    @RequestMapping("/getSetmeal")
    public Result getSetmeal(){
       List<Setmeal> lists =  setmealService.findAll();
       return new Result(true, MessageConstant.QUERY_SETMEALLIST_SUCCESS,lists);
    }
}