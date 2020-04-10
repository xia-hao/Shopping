package com.shopping.mapper;

import com.shopping.domain.District;

import java.util.List;

public interface DistrictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    int insertSelective(District record);

    District selectByPrimaryKey(Integer id);

    List<District> selectDistrictByPid(Integer pid);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);
}