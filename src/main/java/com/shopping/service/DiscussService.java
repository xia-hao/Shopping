package com.shopping.service;

import com.shopping.domain.Discuss;

import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.service
 * @author: XIA
 * @NAME: DiscussService
 * @DATE: 2020/4/7
 **/
public interface DiscussService {

    Map selectDiscussByUidANDPid(Discuss discuss);

    Map addDiscuss(Discuss discuss);

}
