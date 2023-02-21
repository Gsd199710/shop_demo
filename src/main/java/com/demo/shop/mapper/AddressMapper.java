package com.demo.shop.mapper;

import com.demo.shop.entity.Address;
import com.demo.shop.entity.District;

import java.util.List;

/***
 * 收货地址持久层接口
 */
public interface AddressMapper {
    /***
     * 插入用户收货地址数据
     * @param address 用户收货地址数据
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /***
     * 根据用户uid统计收货地址个数
     * @param uid 用户uid
     * @return 受影响的行数
     */
    Integer countByUid(Integer uid);

    /***
     * 根据用户uid查询用户的所有收货地址
     * @param uid 用户uid
     * @return 用户收货地址集合
     */
    List<Address> findByUid(Integer uid);
}
