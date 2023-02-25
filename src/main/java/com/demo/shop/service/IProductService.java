package com.demo.shop.service;

import com.demo.shop.entity.Product;

import java.util.List;

public interface IProductService {

    List<Product> findHotList();

    Product findById(Integer id);
}
