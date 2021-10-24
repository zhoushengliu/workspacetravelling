package com.atguigu.dao;

import com.atguigu.pojo.TravelGroup;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * TravelgroupDao
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-07-27
 * @Description:
 */
public interface TravelgroupDao {
    void add(TravelGroup travelGroup);

    void setTravelgroupAndTravelitem(Map map);

    Page<TravelGroup> findPage(String queryString);

    TravelGroup findById(Integer id);

    List<Integer> findTravelItemIdByTravelgroupId(Integer id);

    void edit(TravelGroup travelGroup);

    void deleteTravelgroupAndTravelitem(Integer id);

    List<TravelGroup> findAll();

    List<TravelGroup> findTravelGroupListById(Integer id);



//    void setTravelgroupAndTravelitem(@Param("travelItemId") Integer travelItemId,
//                                     @Param("id")Integer id);
}

