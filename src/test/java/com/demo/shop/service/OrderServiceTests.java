package com.demo.shop.service;

import com.demo.shop.entity.Address;
import com.demo.shop.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTests {
    @Autowired
    private IOrderService orderService;
    @Test
    public void create(){
        Integer[] cids = {4,5,6};
        Order order = orderService.create(10, 25, "钟离", cids);
        System.out.println(order);
    }

}
