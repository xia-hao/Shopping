package com.shopping.controller;

import com.shopping.domain.ShopCart;
import com.shopping.service.ShopCartService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.controller
 * @author: XIA
 * @NAME: ShopCartController
 * @DATE: 2020/4/18
 **/
@RestController
@RequestMapping("/shopCart")
public class ShopCartController {
    @Resource
    private ShopCartService shopCartService;

    @PostMapping("/add")
    public Map addShopCart(@RequestBody ShopCart shopCart){
        return shopCartService.addShopCart(shopCart);
    }

    @GetMapping("/del/{id}")
    public Map delShopCart(@PathVariable Integer id){
        return shopCartService.delShopCart(id);
    }

    @PostMapping("/update")
    public int updateShopCart(@RequestBody ShopCart shopCart){
        return shopCartService.updateShopCart(shopCart);
    }

    @GetMapping("/list")
    public Map list(ShopCart shopCart){
        return shopCartService.selectShopCart(shopCart);
    }

    @GetMapping("/listByIsSelected")
    public Map listByIsSelected(){
        return shopCartService.selectShopCartByIsSelected();
    }

    @GetMapping("/getCount")
    public int getCount(){
        return shopCartService.getShopCartCount();
    }
}
