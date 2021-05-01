package com.shopping.service;

import com.shopping.domain.ProductSku;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.service
 * @author: XIA
 * @NAME: ProductSkuService
 * @DATE: 2020/4/12
 **/
public interface ProductSkuService {

    Map getProductSkuBySpecsAndPid(ProductSku productSku);

    Map getProductSkuById(Integer skuid);

    int addProductSkuNumber(Integer id,Integer productId,Integer productStock);

    int minusProductSkuNumber(Integer id,Integer productId,Integer productStock);
}
