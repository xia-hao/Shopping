package com.shopping.mapper;

import com.shopping.domain.Verify;

public interface VerifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Verify record);

    int insertSelective(Verify record);

    Verify selectByPrimaryKey(Integer id);

    Verify getVerifyByNoteCode(Verify verify);

    int updateVerifyStatuByvalidTime();

    int updateVerifyStatu(Verify verify);

    int updateByPrimaryKeySelective(Verify record);

    int updateByPrimaryKey(Verify record);
}