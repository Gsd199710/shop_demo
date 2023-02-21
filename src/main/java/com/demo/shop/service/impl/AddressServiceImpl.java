package com.demo.shop.service.impl;

import com.demo.shop.controller.ex.AddressCountLimitedEXception;
import com.demo.shop.entity.Address;
import com.demo.shop.mapper.AddressMapper;
import com.demo.shop.service.IAddressService;
import com.demo.shop.service.IDistrictService;
import com.demo.shop.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/***
 * 收货地址业务层实现类
 */
@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    //添加用户的收货地址的业务层依赖于IDistrictService的业务层接口
    private IDistrictService districtService;
    @Value("${user.address.max-count}")
    private Integer addressMaxCount;
    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        //添加收货地址前先查询用户添加过地址数量
        Integer count = addressMapper.countByUid(uid);
        if (count > addressMaxCount) {
            throw new AddressCountLimitedEXception("该用户收货地址已达上限！");
        }
        //将address对象中的省市区数据补全
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        //补全用户uid、isDefault、以及4项日志属性
        address.setUid(uid);
        Integer isDefault = count == 0 ? 1 : 0 ;
        address.setIsDefault(isDefault);
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());
        Integer rows = addressMapper.insert(address);
        if (rows != 1) {
            throw new InsertException("用户收货地址添加过程出现未知错误！");
        }
    }

    @Override
    public List<Address> findByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        list.stream().map(e->{
            e.setAid(null);
            e.setUid(null);
            e.setProvinceCode(null);
            e.setCityCode(null);
            e.setAreaCode(null);
            e.setTel(null);
            e.setIsDefault(null);
            e.setCreatedTime(null);
            e.setCreatedUser(null);
            e.setModifiedTime(null);
            e.setModifiedUser(null);
            return e;
        }).collect(Collectors.toList());
        return list;
    }
}
