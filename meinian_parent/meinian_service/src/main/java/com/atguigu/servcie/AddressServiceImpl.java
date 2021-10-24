package com.atguigu.servcie;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.AddressDao;
import com.atguigu.pojo.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AddressServiceImpl
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-08-03
 * @Description:
 */
@Service(interfaceClass = AddressService.class)
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Override
    public List<Address> findAll() {
        return addressDao.findAll();
    }
}