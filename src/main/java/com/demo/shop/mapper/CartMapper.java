package com.demo.shop.mapper;

import com.demo.shop.vo.CartVo;
import com.demo.shop.entity.Cart;

import java.util.Date;
import java.util.List;

public interface CartMapper {
    /**
     * 插入购物车数据
     * @param cart 购物车数据
     * @return 受影响的行数
     */
    Integer insert(Cart cart);

    /**
     * 查询用户的购物车记录
     * @param uid 用户id
     * @param pid 商品id
     * @return 购物车数据
     */
    Cart findCartByPid(Integer uid,Integer pid);

    /**
     * 更新购物车商品数量
     * @param cid 购物车id
     * @param num 商品总数
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateCartByCid(Integer cid,
                            Integer num,
                            String modifiedUser,
                            Date modifiedTime);
    List<CartVo> findVOByUid(Integer uid);

    Cart findByCid(Integer cid);

    List<CartVo> findVOByCid(Integer[] cids);
}
