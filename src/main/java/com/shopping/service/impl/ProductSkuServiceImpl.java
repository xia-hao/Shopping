package com.shopping.service.impl;

import com.shopping.domain.ProductSku;
import com.shopping.mapper.ProductSkuMapper;
import com.shopping.service.ProductSkuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.service.impl
 * @author: XIA
 * @NAME: ProductSkuServiceImpl
 * @DATE: 2020/4/12
 **/
@Service
public class ProductSkuServiceImpl implements ProductSkuService {
    @Resource
    private ProductSkuMapper productSkuMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map getProductSkuBySpecsAndPid(ProductSku productSku) {
        Map<String,Object> map = new HashMap<>();
        ProductSku productSku1 = productSkuMapper.getProductSkuBySpecsAndPid(productSku);
        map.put("code","901");
        if(null!=productSku1){
            map.put("code","1000");
            map.put("productSku",productSku1);
        }
        return map;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map getProductSkuById(Integer skuid) {
        Map<String,Object> map = new HashMap<>();
        ProductSku productSku = productSkuMapper.selectByPrimaryKey(skuid);
        map.put("code","901");
        if(null!=productSku){
            map.put("code","1000");
            map.put("productSku",productSku);
        }
        return map;
    }
}
