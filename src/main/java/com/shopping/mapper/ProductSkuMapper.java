package com.shopping.mapper;

import com.shopping.domain.ProductSku;
import org.apache.ibatis.annotations.Param;

public interface ProductSkuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductSku record);

    int insertSelective(ProductSku record);

    ProductSku selectByPrimaryKey(Integer id);

    ProductSku getProductSkuBySpecsAndPid(ProductSku productSku);

    int addProductSkuNumber(@Param("id")Integer id,
                         @Param("productId")Integer productId,
                         @Param("productStock")Integer productStock);

    int minusProductSkuNumber(@Param("id")Integer id,
                         @Param("productId")Integer productId,
                         @Param("productStock")Integer productStock);

    int updateByPrimaryKeySelective(ProductSku record);

    int updateByPrimaryKey(ProductSku record);
}