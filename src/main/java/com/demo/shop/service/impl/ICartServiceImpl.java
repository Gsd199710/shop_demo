package com.demo.shop.service.impl;

import com.demo.shop.entity.Cart;
import com.demo.shop.entity.Product;
import com.demo.shop.mapper.CartMapper;
import com.demo.shop.mapper.ProductMapper;
import com.demo.shop.service.ICartService;
import com.demo.shop.service.ex.InsertException;
import com.demo.shop.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ICartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;
    /**
     * 购物车的业务层依赖于购物车的持久层和商品的持久层
     */
    @Autowired
    private ProductMapper productMapper;
    @Override
    public void addToCart(Integer uid, Integer pid, Integer sum, String username) {
        //先确定要添加的商品是否存在
        Date date = new Date();
        Cart result = cartMapper.findCartByPid(uid, pid);
        if (result == null) {//该商品不存在,添加即可
            Cart cart = new Cart();
            //补全参数数据
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(sum);
            //获取商品中的数据
            Product product = productMapper.findById(pid);
            cart.setPrice(product.getPrice());
            //补全四项日志
            cart.setCreatedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedUser(username);
            cart.setModifiedTime(date);
            //执行插入
            Integer rows = cartMapper.insert(cart);
            if (rows == null) {
                throw new InsertException("添加购物车失败！");
            }
        }else {//已存在数据则进行更新操作
            Integer num = result.getNum()+sum;
            Integer now = cartMapper.updateCartByCid(
                    result.getCid(),
                    num,
                    username,
                    date
            );
            if (now == null) {
                throw new UpdateException("数据更新失败！");
            }
        }
    }
}
