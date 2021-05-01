package com.shopping.utils.quartz.Job;

import com.shopping.domain.OrderInfo;
import com.shopping.service.OrderService;
import com.shopping.service.UserService;
import com.shopping.service.VerifyService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;

/**
 * @PACKAGE_NAME: com.shopping.utils.quartz
 * @author: XIA
 * @NAME: Verify
 * @DATE: 2020/3/25
 **/
public class VerifyJob implements org.quartz.Job {
    @Resource
    private VerifyService verifyService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        int verifyCount = verifyService.updateVerifyStatuByvalidTime();
        if(verifyCount>0){
            System.out.println("已过期的验证码有："+verifyCount+"个！");
        }
    }
}
