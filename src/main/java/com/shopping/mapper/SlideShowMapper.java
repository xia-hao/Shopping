package com.shopping.mapper;

import com.shopping.domain.SlideShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SlideShowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SlideShow record);

    int insertSelective(SlideShow record);

    List<SlideShow> getBySlideShowByProductidANDType(@Param("pid") Integer pid,@Param("type")String type);

    int updateByPrimaryKeySelective(SlideShow record);

    int updateByPrimaryKey(SlideShow record);
}