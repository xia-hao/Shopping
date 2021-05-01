package com.shopping.mapper;

import com.shopping.domain.OrderDetail;

import java.util.List;

public interface OrderDetailMapper {
    List<OrderDetail> selectByOrderNo(String orderNo);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    int deleteByOrderNo(String orderno);
}