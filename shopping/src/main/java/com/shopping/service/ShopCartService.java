package com.shopping.service;

import com.shopping.domain.ShopCart;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.service
 * @author: XIA
 * @NAME: ShopCartService
 * @DATE: 2020/4/18
 **/
public interface ShopCartService {
    /**
     * 查询当前用户的购物车商品
     * @param shopCart
     * @return
     */
    Map selectShopCart(ShopCart shopCart);

    /**
     * 查询需要前往结算的商品
     * @return
     */
    Map selectShopCartByIsSelected();

    /**
     * 查询当前用户购物车总数量
     * @return
     */
    int getShopCartCount();
    /***
     * 添加购物车
     * @return
     */
    Map addShopCart(ShopCart shopCart);

    /***
     * 收藏
     * @param shopCart
     * @return
     */
    Map addCollectible(ShopCart shopCart);
    /***
     * 移除购物车
     * @return
     */
    Map delShopCart(Integer id);

    /**
     * 修改购物车
     * @param shopCart
     * @return
     */
    int updateShopCart(ShopCart shopCart);

    int delShopCart(Integer userId,Integer productId,Integer skuId);
}
