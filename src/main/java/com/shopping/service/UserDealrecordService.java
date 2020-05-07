package com.shopping.service;

import com.shopping.domain.User;
import com.shopping.domain.UserDealrecord;

import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.service
 * @author: XIA
 * @NAME: UserDealrecordService
 * @DATE: 2020/4/23
 **/
public interface UserDealrecordService {
    int adduserDealrecord(UserDealrecord userDealrecord);

    Map selectUserDealrecord(UserDealrecord userDealrecord);
}
