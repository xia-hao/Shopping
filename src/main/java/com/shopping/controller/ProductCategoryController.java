package com.shopping.controller;

import com.shopping.domain.ProductCategory;
import com.shopping.service.ProductCategoryService;
import com.shopping.utils.ProductCategoryVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.controller
 * @author: XIA
 * @NAME: ProductCategoryController
 * @DATE: 2020/3/14
 **/
@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {
    @Resource
    private ProductCategoryService productCategoryService;

    @RequestMapping("/all")
    public Map list(){
        Map<String,Object> map = new HashMap<>();
        map.put("productCategoryVoList",productCategoryService.selectAllProductCategoryList());
        return map;
    }
}
