package com.shopping.service;

import com.shopping.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserService {
    Map dologin(User user);

    User getUserByLoginNameOrMobile(String loginname);

    User getUserById(Integer id);

    int registerUser(User user);

    int updateUserPwdByMobile(String mobile, String password);

    int updateUserFreezing();

    int updateLoginNameByMobile(String loginname,String mobile);
}
