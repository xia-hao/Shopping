package com.shopping.mapper;

import com.shopping.domain.UserDealrecord;

public interface UserDealrecordMapper {
    int insert(UserDealrecord record);

    int insertSelective(UserDealrecord record);
}