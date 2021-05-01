package com.shopping.service;

import com.shopping.domain.OrderInfo;
import com.shopping.domain.PayMent;

import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.service
 * @author: XIA
 * @NAME: PayMentService
 * @DATE: 2020/4/22
 **/
public interface PayMentService {
    Map pay(PayMent payMent);
}
