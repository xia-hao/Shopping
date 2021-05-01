package com.shopping.mapper;

import com.shopping.domain.ShopCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShopCart record);

    int insertSelective(ShopCart record);

    ShopCart selectByPrimaryKey(Integer id);

    List<ShopCart> selectShopCartByUid(Integer userid);

    List<ShopCart> selectShopCartByIsSelected(Integer userid);

    int getShopCartCount(Integer userid);

    ShopCart getShopCartByUidAndPidAndSkuid(ShopCart shopCart);

    int delShopCart(@Param("userId") Integer userId,
                    @Param("productId") Integer productId,
                    @Param("skuId") Integer skuId);

    int updateByPrimaryKeySelective(ShopCart record);

    int updateByPrimaryKey(ShopCart record);
}