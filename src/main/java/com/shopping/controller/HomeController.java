package com.shopping.controller;

import com.shopping.domain.Product;
import com.shopping.service.ProductCategoryService;
import com.shopping.service.ProductService;
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
 * @NAME: HomeController
 * @DATE: 2020/3/15
 **/
@RestController
@RequestMapping("/home")
public class HomeController {
    @Resource
    private ProductCategoryService productCategoryService;
    @Resource
    private ProductService productService;

    @RequestMapping("/all")
    public Map All(){
        Map<String,Object> map = new HashMap<>();
        //查询商品分裂
        List<ProductCategoryVo> productCategoryVoList = productCategoryService.selectAllProductCategoryList();
        //查询一楼
        for (ProductCategoryVo vo : productCategoryVoList) {
            Product product = new Product();
            product.setCategoryId(vo.getProductCategory().getId());
            List<Product> productList = productService.selectProductlist(product);
            vo.setProductList(productList);
        }
        map.put("productCategoryVoList",productCategoryVoList);
        //封装返回
        return map;
    }
}
