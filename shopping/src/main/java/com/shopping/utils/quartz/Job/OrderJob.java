package com.shopping.utils.quartz.Job;

import com.shopping.service.OrderService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;

/**
 * @PACKAGE_NAME: com.shopping.utils.quartz
 * @author: XIA
 * @NAME: OrderJob
 * @DATE: 2020/4/20
 **/
public class OrderJob implements Job {
    @Resource
    private OrderService orderService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        int orderCount = orderService.timingCancelOrder();
        if(orderCount>0){
            System.out.println("订单已超时！");
        }
    }
}
