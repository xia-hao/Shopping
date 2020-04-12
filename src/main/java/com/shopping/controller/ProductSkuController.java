package com.shopping.controller;

import com.shopping.domain.ProductSku;
import com.shopping.service.ProductSkuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.controller
 * @author: XIA
 * @NAME: ProductSkuController
 * @DATE: 2020/4/12
 **/
@RestController
@RequestMapping("/productSku")
public class ProductSkuController {
    @Resource
    private ProductSkuService productSkuService;

    @GetMapping("/BySpecsAndPid")
    public Map BySpecsAndPid(ProductSku productSku){
        return productSkuService.getProductSkuBySpecsAndPid(productSku);
    }

    @GetMapping("/ById/{skuid}")
    public Map ById(@PathVariable Integer skuid){
        return productSkuService.getProductSkuById(skuid);
    }
}
