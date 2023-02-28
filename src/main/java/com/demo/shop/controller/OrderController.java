package com.demo.shop.controller;

import com.demo.shop.entity.Order;
import com.demo.shop.service.IOrderService;
import com.demo.shop.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("orders")
@RestController
public class OrderController extends BaseController{
    @Autowired
    private IOrderService orderService;
    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[]cids, HttpSession session){
        Order data = orderService.create(
                getuidFromSession(session),
                aid,
                getUsernameFromSession(session),
                cids);
        return new JsonResult<>(OK,data);
    }
}
