package com.demo.shop.controller;

import com.demo.shop.entity.Product;
import com.demo.shop.service.IProductService;
import com.demo.shop.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController extends BaseController{
    @Autowired
    private IProductService productService;

    @RequestMapping("hot_list")
    public JsonResult<List<Product>> getHotList(){
        List<Product> data = productService.findHotList();
        return new JsonResult<>(OK,data);
    }
    @GetMapping("details/{id}")
    public JsonResult<Product> details(@PathVariable("id") Integer id){
        Product  product = productService.findById(id);
        return new JsonResult<>(OK,product);
    }
}
