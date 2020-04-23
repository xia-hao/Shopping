package com.shopping.mapper;

import com.shopping.domain.PayMent;

public interface PayMentMapper {
    int insert(PayMent payMent);

    int insertSelective(PayMent payMent);
}