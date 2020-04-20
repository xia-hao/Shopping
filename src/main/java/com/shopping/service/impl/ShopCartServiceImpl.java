package com.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.shopping.domain.ShopCart;
import com.shopping.domain.User;
import com.shopping.mapper.ShopCartMapper;
import com.shopping.service.ShopCartService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.service.impl
 * @author: XIA
 * @NAME: ShopCartServiceImpl
 * @DATE: 2020/4/18
 **/
@Service
public class ShopCartServiceImpl implements ShopCartService {
    @Resource
    private ShopCartMapper shopCartMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Map addShopCart(ShopCart shopCart) {
        Map<String,Object> map = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        shopCart.setUserid(user.getUserid());
        shopCart.setCreatetime(new Date());
        ShopCart result = shopCartMapper.getShopCartByUidAndPidAndSkuid(shopCart);
        map.put("code","901");
        if(null != result){ // 判断是不是同一个商品，如果商品相同则叠加数量
            result.setNumber(shopCart.getNumber()+result.getNumber());
            BigDecimal bigDecimal = new BigDecimal(result.getNumber());
            result.setCost(bigDecimal.multiply(result.getPrice()));
            if(updateShopCart(result)>0){
                map.put("code","1000");
            }
        }else{
            if(shopCartMapper.insertSelective(shopCart)>0){
                map.put("code","1000");
            }
        }
        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Map delShopCart(Integer id) {
        Map<String,Object> map = new HashMap<>();
        map.put("code","901");
        if(shopCartMapper.deleteByPrimaryKey(id)>0){
            map.put("code","1000");
        }
        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateShopCart(ShopCart shopCart) {
        return shopCartMapper.updateByPrimaryKeySelective(shopCart);
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map selectShopCart(ShopCart shopCart) {
        Map<String,Object> map = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        //PageHelper.startPage(shopCart.getPageNo(),shopCart.getPageSize());
        List<ShopCart> shopCartList = shopCartMapper.selectShopCartByUid(user.getUserid());
        map.put("shopCartList",shopCartList);
        map.put("shopCatCount",shopCartMapper.getShopCartCount(user.getUserid()));
        return map;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map selectShopCartByIsSelected() {
        Map<String,Object> map = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<ShopCart> shopCartList = shopCartMapper.selectShopCartByIsSelected(user.getUserid());
        map.put("shopCartList",shopCartList);
        return map;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int getShopCartCount() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        int count = 0;
        if(null != user){
            count = shopCartMapper.getShopCartCount(user.getUserid());
        }
        return count;
    }
}
