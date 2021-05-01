package com.shopping.service;

import com.shopping.domain.Verify;

/**
 * @PACKAGE_NAME: com.shopping.service
 * @author: XIA
 * @NAME: VerifyService
 * @DATE: 2020/3/23
 **/
public interface VerifyService {
    int addVerify(Verify verify);

    Verify getVerifyByNoteCode(Verify verify);

    int updateVerifyStatu(Verify verify);

    int updateVerifyStatuByvalidTime();
}
