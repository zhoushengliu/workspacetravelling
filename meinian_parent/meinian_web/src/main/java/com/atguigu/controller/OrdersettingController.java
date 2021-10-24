package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.servcie.OrdersettingService;
import com.atguigu.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * OrdersettingController
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-07-30
 * @Description:
 */
@RestController
@RequestMapping("/ordersetting")
public class OrdersettingController {

    @Reference
    private OrdersettingService ordersettingService;

    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        ordersettingService.editNumberByDate(orderSetting);
        return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
    }

    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date){
        List<Map> list = ordersettingService.getOrderSettingByMonth(date);
        return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
    }

    @RequestMapping("/upload")
    public Result upload(MultipartFile excelFile){
        try {
            // 读取excel表格的数据
            List<String[]> lists = POIUtils.readExcel(excelFile);
            List<OrderSetting> datas= new ArrayList<>();
            for (String[] list : lists) {
                datas.add(new OrderSetting(new Date(list[0]),Integer.parseInt(list[1])));
            }
            ordersettingService.add(datas);
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);


        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }

    }
}