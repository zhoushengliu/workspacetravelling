package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Address;
import com.atguigu.servcie.AddressService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AddressController
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-08-03
 * @Description:
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Reference
    private AddressService addressService;
//    for(var x=0;x<data.gridnMaps.length;x++){
//        adds.push(new BMap.Point(data.gridnMaps[x].lng,data.gridnMaps[x].lat));
//        addNames.push(data.nameMaps[x].addressName);
//    }
    @RequestMapping("/findAllMaps")
    public Map findAllMaps(){
        List<Address> lists =  addressService.findAll();
        List<Map> gridnMaps = new ArrayList<>();
        List<Map> nameMaps = new ArrayList<>();
        for (Address address : lists) {
            Map map1 = new HashMap<>();
            map1.put("lng",address.getLng());
            map1.put("lat",address.getLat());
            gridnMaps.add(map1);
            Map map2 = new HashMap<>();
            map2.put("addressName",address.getAddressName());
            nameMaps.add(map2);

        }
        Map map = new HashMap<>();
        map.put("gridnMaps",gridnMaps);
        map.put("nameMaps",nameMaps);
        return map;

    }
}