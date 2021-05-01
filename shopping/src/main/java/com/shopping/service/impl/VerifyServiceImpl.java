package com.shopping.service.impl;

import com.shopping.domain.Verify;
import com.shopping.mapper.VerifyMapper;
import com.shopping.service.VerifyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @PACKAGE_NAME: com.shopping.service.impl
 * @author: XIA
 * @NAME: VerifyServiceImpl
 * @DATE: 2020/3/23
 **/
@Service
public class VerifyServiceImpl implements VerifyService {
    @Resource
    private VerifyMapper verifyMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int addVerify(Verify verify) {
        return verifyMapper.insertSelective(verify);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Verify getVerifyByNoteCode(Verify verify) {
        return verifyMapper.getVerifyByNoteCode(verify);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateVerifyStatu(Verify verify) {
        return verifyMapper.updateVerifyStatu(verify);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateVerifyStatuByvalidTime() {
        return verifyMapper.updateVerifyStatuByvalidTime();
    }
}
