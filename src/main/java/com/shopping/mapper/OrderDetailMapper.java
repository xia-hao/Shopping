package com.shopping.mapper;

import com.shopping.domain.OrderDetail;

public interface OrderDetailMapper {
    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);
}