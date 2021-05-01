package com.shopping.mapper;

import com.shopping.domain.UserAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    List<UserAddress> selectUserAddressByUid(Integer userid);

    UserAddress selectByIdAndUserId(@Param("id") Integer id,@Param("userid") Integer userid);

    int updateUserAddressIsDefaultByUserid(Integer userid);

    int updateUserAddressIsDefaultById(Integer id);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);
}