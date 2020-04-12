package com.shopping.mapper;

import com.shopping.domain.ProductSku;

public interface ProductSkuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductSku record);

    int insertSelective(ProductSku record);

    ProductSku selectByPrimaryKey(Integer id);

    ProductSku getProductSkuBySpecsAndPid(ProductSku productSku);

    int updateByPrimaryKeySelective(ProductSku record);

    int updateByPrimaryKey(ProductSku record);
}