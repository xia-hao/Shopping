package com.shopping.service;

import com.shopping.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Map;

public interface UserService {
    Map dologin(User user);

    User getUserByLoginNameOrMobile(String loginname);

    User getUserById(Integer id);

    int registerUser(User user);

    int updateUserPwdByMobile(String mobile, String password);

    int userConsume(Integer userid, BigDecimal balance);

    Map update(User user);

    Map updateUserImage(MultipartFile multipartFile);

    Map updateMobile(User user);

    Map updatePwd(User user);

    int userIncome(Integer userid, BigDecimal balance);

    int updateUserFreezing();

    int updateLoginNameByMobile(String loginname,String mobile);
}
