package com.shopping.mapper;

import com.shopping.domain.Discuss;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DiscussMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Discuss record);

    int insertSelective(Discuss record);

    Discuss selectByPrimaryKey(Integer id);

    List<Discuss> selectDiscussByPid(Integer productid);

    int getDiscussCountByPid(Integer productid);

    int updateByPrimaryKeySelective(Discuss record);

    int updateByPrimaryKey(Discuss record);
}