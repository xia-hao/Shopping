package com.shopping.utils.quartz;

import com.shopping.service.UserService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;

/**
 * @PACKAGE_NAME: com.shopping.utils.quartz
 * @author: XIA
 * @NAME: UserJob
 * @DATE: 2020/3/25
 **/
public class UserJob implements Job {
    @Resource
    private UserService userService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
       /* int count = userService.updateUserFreezing();
        if(count>0){
            System.out.println("从冻结改为正常的账户有："+count+"个！");
        }*/
    }
}
