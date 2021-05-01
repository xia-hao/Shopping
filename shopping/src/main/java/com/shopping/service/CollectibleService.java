package com.shopping.service;

import com.shopping.domain.Collectible;

import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.service
 * @author: XIA
 * @NAME: CollectibleService
 * @DATE: 2020/4/24
 **/
public interface CollectibleService {
    Map addCollectible(Collectible collectible);

    Map delCollectible(Integer productid);

    Map getCollectible(Integer productid);

    Map selectCollectible(Collectible collectible);
}
