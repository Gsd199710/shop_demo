package com.demo.shop.mapper;

import com.demo.shop.entity.Product;

import java.util.List;

public interface ProductMapper {
    /**
     * 查询热销商品
     * @return 商品列表
     */
    List<Product> findHotList();

    /**
     * 根据商品id查询商品详情
     * @param id id
     * @return 商品信息
     */
    Product findById(Integer id);
}
