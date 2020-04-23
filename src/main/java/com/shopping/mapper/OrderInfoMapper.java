package com.shopping.mapper;

import com.shopping.domain.OrderInfo;

import java.util.List;

public interface OrderInfoMapper {
    int deleteByOrderNo(String orderno);

    int insert(OrderInfo orderInfo);

    int insertSelective(OrderInfo orderInfo);

    OrderInfo selectByPrimaryKey(String orderno);

    List<OrderInfo> selectOrderByUidAndStatu(OrderInfo orderInfo);

    int getOrderCount(OrderInfo orderInfo);

    int timingCancelOrder();

    int updateByPrimaryKeySelective(OrderInfo orderInfo);

    int updateByPrimaryKey(OrderInfo orderInfo);

}