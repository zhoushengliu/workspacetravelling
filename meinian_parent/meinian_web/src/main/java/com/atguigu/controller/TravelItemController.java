package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelItem;
import com.atguigu.servcie.TravelItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TravelItemController
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-07-26
 * @Description:
 */
@RequestMapping("/travelItem")
@RestController
public class TravelItemController {

    @Reference
    private TravelItemService travelItemService;

    @RequestMapping("/findAll")
    public Result findAll(){
       List<TravelItem> lists =  travelItemService.findAll();
       return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,lists);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
       TravelItem travelItem =  travelItemService.findById(id);
       return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,travelItem);
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody TravelItem travelItem){
        travelItemService.edit(travelItem);
        return new Result(true,MessageConstant.EDIT_TRAVELITEM_SUCCESS);
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('TRAVELITEM_DELETE111')")
    public Result deleteById(Integer id){
        try {
            travelItemService.deleteById(id);
            return new Result(true,MessageConstant.DELETE_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_TRAVELITEM_FAIL);
        }
    }

    @RequestMapping("/findPage")
    @PreAuthorize("hasAuthority('TRAVELITEM_QUERY')")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
       PageResult pageResult =  travelItemService.findPage(queryPageBean);
       return pageResult;
    }

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('TRAVELITEM_ADD')")
    public Result add(@RequestBody TravelItem travelItem){
        travelItemService.add(travelItem);
        return new Result(true, MessageConstant.ADD_TRAVELITEM_SUCCESS);
    }
}