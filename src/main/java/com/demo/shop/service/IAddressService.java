package com.demo.shop.service;

import com.demo.shop.entity.Address;

import java.util.List;

/***
 * 收货地址业务层接口
 */
public interface IAddressService {
    void addNewAddress(Integer uid, String username, Address address);
    List<Address> findByUid(Integer uid);
}
