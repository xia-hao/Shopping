package com.shopping.service;

import com.shopping.domain.OrderInfo;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.service
 * @author: XIA
 * @NAME: OrderInfoService
 * @DATE: 2020/4/20
 **/
public interface OrderService {
    Map addOrder(OrderInfo orderInfo) throws Exception;

    int timingCancelOrder();

    int updateOrder(OrderInfo orderInfo);

    Map selectOrderByUidAndStatu(OrderInfo orderInfo);

    Map getOrderByOrderNo(String orderNo);

    Map delOrderByOrderNo(String orderNo);
}
