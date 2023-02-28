package com.demo.shop.mapper;

import com.demo.shop.entity.Order;
import com.demo.shop.entity.OrderItem;

/**
 * 订单的持久层接口
 */
public interface OrderMapper {
    /**
     * 插入订单数据
     * @param order 订单数据
     * @return 受影响的行数
     */
    Integer insertOrder(Order order);

    /**
     * 插入订单项数据
     * @param orderItem 订单项数据
     * @return 受影响的行数
     */
    Integer insertOrderItem(OrderItem orderItem);
}
