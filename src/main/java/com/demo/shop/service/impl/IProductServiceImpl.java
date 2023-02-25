package com.demo.shop.service.impl;

import com.demo.shop.entity.Product;
import com.demo.shop.mapper.ProductMapper;
import com.demo.shop.service.IProductService;
import com.demo.shop.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> findHotList() {
        List<Product> hotList = productMapper.findHotList();
        List<Product> collect = hotList.stream().map(e -> {
            e.setPriority(null);
            e.setCreatedTime(null);
            e.setCreatedUser(null);
            e.setModifiedTime(null);
            e.setModifiedUser(null);
            return e;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public Product findById(Integer id) {
        Product product = productMapper.findById(id);
        if (product == null) {
            throw new ProductNotFoundException("该商品不存在！");
        }
        //设置部分属性为空，在页面上不展示这些数据
        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);
        return product;
    }
}
