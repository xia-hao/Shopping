package com.shopping.controller;

import com.github.pagehelper.PageHelper;
import com.shopping.domain.Product;
import com.shopping.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.controller
 * @author: XIA
 * @NAME: ProductController
 * @DATE: 2020/3/15
 **/
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    @RequestMapping("/list")
    public Map list(Product product){
        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(product.getPageNo(),product.getPageSize());
        List<Product> productsList = productService.selectProductlist(product);
        map.put("productList",productsList);
        map.put("productCount",productService.getProductCount(product));
        return map;
    }

    @RequestMapping("productById/{id}")
    public Map productById(@PathVariable Integer id){
        Map<String,Object> map = new HashMap<>();
        map.put("getproduct",productService.getProductById(id));
        return map;
    }
}
