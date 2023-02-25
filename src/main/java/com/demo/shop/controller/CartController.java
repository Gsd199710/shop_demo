package com.demo.shop.controller;

import com.demo.shop.service.ICartService;
import com.demo.shop.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("carts")
@RestController
public class CartController extends BaseController{
    @Autowired
    private ICartService cartService;
    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid,
                                      Integer sum,
                                      HttpSession session){
        cartService.addToCart(
                getuidFromSession(session),
                pid,
                sum,
                getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
}
