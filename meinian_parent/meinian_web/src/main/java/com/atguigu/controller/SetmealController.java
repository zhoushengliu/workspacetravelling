package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.constant.RedisConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Setmeal;
import com.atguigu.servcie.SetmealService;
import com.atguigu.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.UUID;

/**
 * SetmealController
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-07-28
 * @Description:
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
       PageResult pageResult =  setmealService.findPage(queryPageBean);
       return pageResult;
    }


    @RequestMapping("/add")
    public Result add(Integer[] travelgroupIds, @RequestBody Setmeal setmeal){
        setmealService.add(setmeal,travelgroupIds);
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    @RequestMapping("/upload")
    public Result upload(MultipartFile imgFile){
        try {
            // 获取原始图片的名称 abc.jpg
            String originalFilename = imgFile.getOriginalFilename();
            // 获取图片名字里面的.
            int lastIndexOf = originalFilename.lastIndexOf(".");
            // 获取文件的后缀
            String substring = originalFilename.substring(lastIndexOf);
            String fileName =  UUID.randomUUID().toString() + substring;
            // 上传图片，七牛云
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),fileName);
            // 往七牛云存的同时，顺便往redis数据库存图片
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }

    }
}