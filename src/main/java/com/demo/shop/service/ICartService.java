package com.demo.shop.service;

public interface ICartService {
    /**
     * 添加购物车
     * @param uid 用户id
     * @param pid 商品id
     * @param sum 商品数量
     * @param username 用户名
     */
    void addToCart(Integer uid,Integer pid,Integer sum,String username);
}
