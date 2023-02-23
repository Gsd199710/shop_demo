package com.demo.shop.service;

import com.demo.shop.entity.Address;

import java.util.List;

/***
 * 收货地址业务层接口
 */
public interface IAddressService {
    void addNewAddress(Integer uid, String username, Address address);
    List<Address> findByUid(Integer uid);

    /**
     * 修改指定用户的某个收货地址为默认的
     * @param uid 用户uid
     * @param aid 用户地址aid
     * @param username 修改人
     */
    void setDefault(Integer uid,Integer aid,String username);

    /**
     * 删除用户指定aid的收货地址
     * @param uid 用户id
     * @param aid aid 地址aid
     * @param username 修改人
     */
    void delete(Integer uid,Integer aid,String username);
//    Address getByAid(Integer uid,Integer aid);
    int getIsDefault(Integer aid);
}
