package com.shopping.controller;

import com.shopping.domain.Collectible;
import com.shopping.service.CollectibleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.controller
 * @author: XIA
 * @NAME: CollectibleController
 * @DATE: 2020/4/24
 **/
@RestController
@RequestMapping("/collectible")
public class CollectibleController {
    @Resource
    private CollectibleService collectibleService;

    @PostMapping("/add")
    public Map add(@RequestBody Collectible collectible){
        return collectibleService.addCollectible(collectible);
    }

    @GetMapping("/del/{productId}")
    public Map del(@PathVariable Integer productId){
        return collectibleService.delCollectible(productId);
    }

    @GetMapping("/get/{productId}")
    public Map get(@PathVariable Integer productId){
        return collectibleService.getCollectible(productId);
    }

    @GetMapping("/list")
    public Map get(Collectible collectible){
        return collectibleService.selectCollectible(collectible);
    }
}
