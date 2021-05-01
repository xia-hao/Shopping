package com.shopping.mapper;

import com.shopping.domain.Collectible;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectibleMapper {
    Collectible getCollectibleByPid(@Param("userid")Integer userid , @Param("productid") Integer productid);

    List<Collectible> selectCollectibleByUid(Integer userid);

    int getCollectibleCount(Integer userid);

    int insert(Collectible record);

    int delCollectibleByUidAndPid(@Param("userid")Integer userid , @Param("productid") Integer productid);

    int insertSelective(Collectible record);
}