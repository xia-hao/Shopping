package com.shopping.mapper;

import com.shopping.domain.OrderInfo;

public interface OrderInfoMapper {
    int deleteByPrimaryKey(String orderno);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(String orderno);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);
}