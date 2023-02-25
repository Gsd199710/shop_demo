package com.demo.shop.mapper;

import com.demo.shop.entity.Cart;
import com.demo.shop.entity.User;
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
public class CartMapperTests {
    @Autowired
    //报错的原因是：接口本来不能创建bean，这里有Mybatis的动态代理实现创建bean的过程
    private CartMapper cartMapper;
    @Test
    public void insert(){
        Cart cart = new Cart();
        cart.setUid(11);
        cart.setPid(1001);
        cart.setNum(2);
        cart.setPrice(4399L);
        cartMapper.insert(cart);
    }
    @Test
    public void findByPid(){
        Cart cartByPid = cartMapper.findCartByPid(11, 1001);
        System.out.println(cartByPid);
    }
    @Test
    public void updateCartByUid(){
        cartMapper.updateCartByCid(1,5,"夏洛",new Date());
    }
    @Test
    public void findByUid(){
        List<CartVo> voByUid = cartMapper.findVOByUid(10);
        for (CartVo list: voByUid) {
            System.out.println(voByUid);
        }
    }
    @Test
    public void findByVoCid(){
        List<CartVo> result = cartMapper.findVOByCid(new Integer[]{1, 2, 3});
        for (CartVo list: result
             ) {
            System.out.println(list);
        }
    }
}
