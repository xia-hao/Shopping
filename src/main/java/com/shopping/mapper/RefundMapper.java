package com.shopping.mapper;

import com.shopping.domain.Refund;

public interface RefundMapper {
    int deleteByPrimaryKey(String refundNo);

    int insert(Refund record);

    int insertSelective(Refund record);

    Refund selectByPrimaryKey(String refundNo);

    int updateByPrimaryKeySelective(Refund record);

    int updateByPrimaryKey(Refund record);
}