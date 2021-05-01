package com.shopping.mapper;

import com.shopping.domain.User12312;

public interface User12312Mapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User12312 record);

    int insertSelective(User12312 record);

    User12312 selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User12312 record);

    int updateByPrimaryKey(User12312 record);
}