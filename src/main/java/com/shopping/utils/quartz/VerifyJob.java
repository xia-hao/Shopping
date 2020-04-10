package com.shopping.utils.quartz;

import com.shopping.service.VerifyService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;

/**
 * @PACKAGE_NAME: com.shopping.utils.quartz
 * @author: XIA
 * @NAME: Verify
 * @DATE: 2020/3/25
 **/
public class VerifyJob implements Job {
    @Resource
    private VerifyService verifyService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        /*int count = verifyService.updateVerifyStatuByvalidTime();
        if(count>0){
            System.out.println("已过期的验证码有："+count+"个！");
        }*/
    }
}
