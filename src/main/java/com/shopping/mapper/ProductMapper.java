package com.shopping.mapper;

import com.shopping.domain.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product product);

    int insertSelective(Product product);

    Product selectByPrimaryKey(Integer id);

    List<Product> selectProductlist(@Param("name")String name, @Param("categoryId")Integer categoryId,
                                    @Param("price1")Float price1, @Param("price2")Float price2);

    int getProductCount(@Param("name")String name,@Param("categoryId")Integer categoryId,
                        @Param("price1")Float price1,@Param("price2")Float price2);

    int updateByPrimaryKeySelective(Product product);

    int updateByPrimaryKey(Product product);
}