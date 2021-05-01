package com.shopping.mapper;

import com.shopping.domain.UserDealrecord;

import java.util.List;

public interface UserDealrecordMapper {
    List<UserDealrecord> selectUserDealrecord(Integer userid);

    int getUserDealrecordCount(Integer userid);

    int insert(UserDealrecord record);

    int insertSelective(UserDealrecord record);
}