package com.demo.shop.mapper;

import com.demo.shop.entity.Cart;
import com.demo.shop.entity.Order;
import com.demo.shop.entity.OrderItem;
import com.demo.shop.vo.CartVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest //表示当前类是测试类，不会随项目一块打包
@RunWith(SpringRunner.class)//表示启动这个单元测试类(没有则无法运行)，必须传递一个Spring Runner的实例类型
public class OrderMapperTests {
    @Autowired
    //报错的原因是：接口本来不能创建bean，这里有Mybatis的动态代理实现创建bean的过程
    private OrderMapper orderMapper;
    @Test
    public void insertorder(){
        Order order = new Order();
        order.setUid(10);
        order.setRecvName("钟离");
        order.setRecvPhone("19343467");
        System.out.println(orderMapper.insertOrder(order));
    }
    @Test
    public void insertorderItem(){
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(10000026);
        orderItem.setTitle("联想（Lenovo）YOGA710 14英寸（i7-7500U 8G 256GSSD 2G独显）金色");
        orderItem.setPrice(4399L);
        orderItem.setNum(3);
        System.out.println(orderMapper.insertOrderItem(orderItem));
    }
}
