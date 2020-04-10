package com.shopping.mapper;

import com.shopping.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    User getUserByLoginNameOrMobile(String loginname);

    int updateUserStatu(User user);

    int updateUserFreezing();

    int updateUserPwdByMobile(@Param("mobile")String mobile,@Param("password")String password);

    int updateLoginNameByMobile(@Param("loginname")String loginname,@Param("mobile")String mobile);

    int updateUserPwdErrorCount(String loginName);

    int updateUserPwdErrorCountByLoginNameOrMobile(String loginName);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}