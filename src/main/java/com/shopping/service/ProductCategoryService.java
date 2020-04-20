package com.shopping.service;

import com.shopping.domain.ProductCategory;
import com.shopping.utils.utils.ProductCategoryVo;

import java.util.List;

/**
 * @PACKAGE_NAME: com.shopping.service
 * @author: XIA
 * @NAME: ProductCategoryService
 * @DATE: 2020/3/14
 **/
public interface ProductCategoryService {
    List<ProductCategory> selectByParentId(Integer parentId);
    List<ProductCategoryVo> selectAllProductCategoryList();
}
