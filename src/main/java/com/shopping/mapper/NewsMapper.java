package com.shopping.mapper;

import com.shopping.domain.News;

import java.util.List;

public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News getNewsById(Integer id);

    int getNewsCount();

    List<News> selectNewsList();


    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);
}