package com.demo.shop.service.impl;

import com.demo.shop.entity.Address;
import com.demo.shop.entity.Order;
import com.demo.shop.entity.OrderItem;
import com.demo.shop.mapper.OrderMapper;
import com.demo.shop.service.IAddressService;
import com.demo.shop.service.ICartService;
import com.demo.shop.service.IOrderService;
import com.demo.shop.service.ex.CartNotFountException;
import com.demo.shop.service.ex.InsertException;
import com.demo.shop.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    //IOrderService的业务依赖于IAddressService接口以及ICartService接口
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;
    @Override
    public Order create(Integer uid, Integer aid, String username, Integer[] cids) {
        //创建订单前先确认购物车存在
        //即将要下单的列表
        List<CartVo> cartVoList = cartService.getVOByCid(uid, cids);

        if (cartVoList == null) {
            throw new CartNotFountException("该购物车数据不存在！");
        }
        //计算购物车商品总价值
        Long totalPrice = 0L;
        for (CartVo list:cartVoList) {
            totalPrice += list.getRealprice()*list.getNum();
        }
        Address address = addressService.getByAid(aid,uid);
        Order order = new Order();
        order.setUid(uid);
        //补全收货地址
        order.setRecvProvince(address.getProvinceName());
        order.setRecvPhone(address.getPhone());
        order.setRecvName(address.getName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        //补全价格信息
        order.setStatus(0);
        order.setTotalPrice(0L);
        //补全日志信息
        order.setCreatedUser(address.getCreatedUser());
        order.setCreatedTime(address.getCreatedTime());
        order.setModifiedUser(address.getModifiedUser());
        order.setModifiedTime(address.getModifiedTime());

        //插入数据
        Integer rows = orderMapper.insertOrder(order);
        if (rows != 1) {
            throw new InsertException("插入数据异常");
        }

        for (CartVo list:cartVoList) {
            totalPrice += list.getRealprice()*list.getNum();
            //创建订单项数据
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setPid(list.getPid());
            orderItem.setTitle(list.getTitle());
            orderItem.setImage(list.getImage());
            orderItem.setPrice(list.getRealprice());
            orderItem.setNum(list.getNum());
            //补全日志
            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(new Date());
            orderItem.setModifiedUser(username);
            orderItem.setModifiedTime(new Date());
            //完成插入
           rows = orderMapper.insertOrderItem(orderItem);
            if (rows != 1) {
                throw new InsertException("插入数据出现未知异常！");
            }
        }
        return order;
    }
}
