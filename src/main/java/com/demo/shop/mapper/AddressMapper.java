package com.demo.shop.mapper;

import com.demo.shop.entity.Address;
import com.demo.shop.entity.District;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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
    /**
     * 根据aid查询用户收货地址
     * @param aid aid
     * @return 地址数据对象
     */
    Address findByAid(Integer aid);
    /**
     * 根据用户uid修改收货地址为非默认
     * @param uid uid
     * @return 修改后影响的行数
     */
    Integer updateNonDefault(Integer uid);
    /**
     * 根据地址aid修改该地址为默认
     * @param aid aid
     * @return 修改后影响的行数
     */
    Integer updateDefaultByAid(@Param("aid") Integer aid,
                               @Param("modifiedUser") String modifiedUser,
                               @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据收货地址aid删除指定地址数据
     * @param aid aid
     * @return 受影响的行数
     */
    Integer deleteByAid(Integer aid);

    /**
     * 查询用户最后一次修改的地址数据
     * @param uid uid
     * @return 收货地址数据
     */
    Address findLastModified(Integer uid);
    Integer getIsDefaultByAid(Integer aid);
}
