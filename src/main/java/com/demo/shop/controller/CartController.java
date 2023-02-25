package com.demo.shop.controller;

import com.demo.shop.service.ICartService;
import com.demo.shop.util.JsonResult;
import com.demo.shop.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    @RequestMapping("/num/add/{cid}")
    public JsonResult<Integer> addNum(
                                    @PathVariable("cid")Integer cid,
                                    HttpSession session){
        Integer data = cartService.addNum(
                cid,
                getuidFromSession(session),
                getUsernameFromSession(session));

        return new JsonResult<>(OK,data);
    }
    @RequestMapping("/num/reduce/{cid}")
    public JsonResult<Integer> reduceNum(
            @PathVariable("cid")Integer cid,
            HttpSession session){
        Integer data = cartService.reduceNum(
                cid,
                getuidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<>(OK,data);
    }
    @RequestMapping({"","/"})
    public JsonResult<List<CartVo>> getCartVoByUid(HttpSession session){
        List<CartVo> data = cartService.getVOByUid(getuidFromSession(session));
        return new JsonResult<>(OK,data);
    }
    @RequestMapping("list")
    public JsonResult<List<CartVo>> getCartVoByCid(
                                    Integer[] cids,
                                    HttpSession session){
        List<CartVo> data = cartService.getVOByCid(getuidFromSession(session),cids);
        return new JsonResult<>(OK,data);
    }
}
