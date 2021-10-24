package com.atguigu.job;

import com.atguigu.constant.RedisConstant;
import com.atguigu.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Iterator;
import java.util.Set;

/**
 * ClearImgJob
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-07-30
 * @Description:
 */
public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 删除图片
     * ① 删除七牛云图片
     * ② 删除数据库图片
     */
    public void clearImg(){
        // 集合大的放到前面
        Set<String> set = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES,
                RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            String image = iterator.next();
            System.out.println("需要删除的图片名称：" + image);
            // 删除七牛云图片
            QiniuUtils.deleteFileFromQiniu(image);
            // 删除数据库图片
            jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,image);
        }

    }
}