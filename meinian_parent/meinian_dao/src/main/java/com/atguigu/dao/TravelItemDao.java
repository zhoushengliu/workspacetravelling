package com.atguigu.dao;

import com.atguigu.pojo.TravelGroup;
import com.atguigu.pojo.TravelItem;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * TravelItemDao
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-07-26
 * @Description:
 */
public interface TravelItemDao {

    List<TravelItem> findTravelItemListById(Integer id);
    void add(TravelItem travelItem);

    Page<TravelItem> findPage(String queryString);

    void deleteById(Integer id);

    long findTravelgroupAndTravelitem(Integer id);

    TravelItem findById(Integer id);

    void edit(TravelItem travelItem);

    List<TravelItem> findAll();

}

