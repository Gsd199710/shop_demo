package com.demo.shop.service;

import com.demo.shop.entity.Address;
import com.demo.shop.entity.Order;

/**
 * 创建订单的接口
 */
public interface IOrderService {
    Order create(Integer uid, Integer aid, String username, Integer []cids);
}
