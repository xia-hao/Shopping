package com.shopping.service;

import com.shopping.domain.UserAddress;

import java.util.List;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.service
 * @author: XIA
 * @NAME: UserAddressService
 * @DATE: 2020/4/5
 **/
public interface UserAddressService {
    Map selectUserAddressByUid(Integer uid);

    Map addUserAddress(UserAddress userAddress);

    Map updateUserAddress(UserAddress userAddress);

    Map getUserAddressById(Integer id);

    Map updateUserAddressIsDefault(Integer id);

    Map delUserAddress(Integer id);

    int updateUserAddressIsDefaultByUserid(Integer userid);

    int updateUserAddressIsDefaultById(Integer id);
}
