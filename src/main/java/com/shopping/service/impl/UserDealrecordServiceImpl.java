package com.shopping.service.impl;

import com.shopping.domain.UserDealrecord;
import com.shopping.mapper.UserDealrecordMapper;
import com.shopping.service.UserDealrecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @PACKAGE_NAME: com.shopping.service.impl
 * @author: XIA
 * @NAME: UserDealrecordServiceImpl
 * @DATE: 2020/4/23
 **/
@Service
public class UserDealrecordServiceImpl implements UserDealrecordService {
    @Resource
    private UserDealrecordMapper userDealrecordMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int adduserDealrecord(UserDealrecord userDealrecord) {
        return userDealrecordMapper.insert(userDealrecord);
    }
}
