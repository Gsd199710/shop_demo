package com.demo.shop.service.impl;

import com.demo.shop.entity.Cart;
import com.demo.shop.entity.Product;
import com.demo.shop.mapper.CartMapper;
import com.demo.shop.mapper.ProductMapper;
import com.demo.shop.service.ICartService;
import com.demo.shop.service.ex.AccessDeniedException;
import com.demo.shop.service.ex.CartNotFountException;
import com.demo.shop.service.ex.InsertException;
import com.demo.shop.service.ex.UpdateException;
import com.demo.shop.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

    @Override
    public List<CartVo> getVOByUid(Integer uid) {
        List<CartVo> data = cartMapper.findVOByUid(uid);
        return data;
    }

    @Override
    public Integer addNum(Integer cid,Integer uid,String username) {
        Cart cart = cartMapper.findByCid(cid);
        if (cart == null) {
            throw new CartNotFountException("该数据不存在！");
        }
        if (!uid.equals(cart.getUid())){
            throw new AccessDeniedException("非法访问！");
        }
        Integer num = cart.getNum()+1;
        Integer rows = cartMapper.updateCartByCid(cid, num, username, new Date());
        if (rows != 1) {
            throw new UpdateException("修改时出现未知异常！");
        }
        return num;
    }

    @Override
    public Integer reduceNum(Integer cid, Integer uid, String username) {
        Cart cart = cartMapper.findByCid(cid);
        if (cart == null) {
            throw new CartNotFountException("该数据不存在！");
        }
        if (!uid.equals(cart.getUid())){
            throw new AccessDeniedException("非法访问！");
        }
        if (cart.getNum() < 1) {
            throw new UpdateException("购物车数据不存在!");
        }
        Integer num = cart.getNum()-1;
        Integer rows = cartMapper.updateCartByCid(cid, num, username, new Date());
        if (rows != 1) {
            throw new UpdateException("数据更新出现未知异常！");
        }
        return num;
    }

    @Override
    public List<CartVo> getVOByCid(Integer uid, Integer[] cids) {
        List<CartVo> list = cartMapper.findVOByCid(cids);
        Iterator<CartVo> iterator = list.iterator();
        while (iterator.hasNext()){
            CartVo cartVo = iterator.next();
            if (!cartVo.getUid().equals(uid)) {
                //将不属于当前用户的购物车数据删除
                list.remove(cartVo);
            }
        }
        return list;
    }
}
