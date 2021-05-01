package com.shopping.utils.quartz;

import com.shopping.utils.quartz.Job.OrderJob;
import com.shopping.utils.quartz.Job.UserJob;
import com.shopping.utils.quartz.Job.VerifyJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @PACKAGE_NAME: com.shopping.utils.quartz
 * @author: XIA
 * @NAME: MyScheduler
 * @DATE: 2020/3/25
 **/
@Component
public class MyScheduler {
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    public void scheduler() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        startJob1(scheduler);
        startJob2(scheduler);
        startJob3(scheduler);
    }

    public void startJob1(Scheduler scheduler) throws SchedulerException {
        //创建实例
        JobDetail jobDetail = JobBuilder.newJob(UserJob.class).withIdentity("job1","group1").build();
        //执行时间 秒 分 时 日 月 周 年    ?：匹配该域的任意值。月份的天和周的天互相冲突，必须将其中一个设置为?
        // 每一分钟的00秒启动
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ?");
        //创建触发器
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1")
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }

    public void startJob2(Scheduler scheduler) throws SchedulerException {
        //创建实例
        JobDetail jobDetail = JobBuilder.newJob(VerifyJob.class).withIdentity("job2","group2").build();
        //执行时间 秒 分 时 日 月 周 年    ?：匹配该域的任意值。月份的天和周的天互相冲突，必须将其中一个设置为?
        // 每一分钟的00秒启动
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ?");
        //创建触发器
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2","group2")
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }

    public void startJob3(Scheduler scheduler) throws SchedulerException {
        //创建实例
        JobDetail jobDetail = JobBuilder.newJob(OrderJob.class).withIdentity("job3","group3").build();
        //执行时间 秒 分 时 日 月 周 年    ?：匹配该域的任意值。月份的天和周的天互相冲突，必须将其中一个设置为?
        // 每一分钟的00秒启动
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ?");
        //创建触发器
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger3","group3")
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }
}
