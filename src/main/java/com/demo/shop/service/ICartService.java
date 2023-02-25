package com.demo.shop.service;

import com.demo.shop.vo.CartVo;

import java.util.List;

public interface ICartService {
    /**
     * 添加购物车
     * @param uid 用户id
     * @param pid 商品id
     * @param sum 商品数量
     * @param username 用户名
     */
    void addToCart(Integer uid,Integer pid,Integer sum,String username);

    /**
     * 根据用户id获取购物车详细信息
     * @param uid 用户id
     * @return 购物车信息
     */
    List<CartVo> getVOByUid(Integer uid);

    /**
     * 根据购物车cid添加指定商品的数量
     * @param cid 购物车cid
     * @return 受影响的行数
     */
    Integer addNum(Integer cid,Integer uid,String username);
    Integer reduceNum(Integer cid,Integer uid,String username);

    List<CartVo> getVOByCid(Integer uid, Integer[] cids);
}
