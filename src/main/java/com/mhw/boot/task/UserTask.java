package com.mhw.boot.task;

import com.mhw.boot.service.UserService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务类
 *
 * @Author mhw_mhw
 * @Date 2018/8/20 10:47
 * @Version
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
public class UserTask extends QuartzJobBean {

    @Resource
    private UserService userService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("执行时间：" + dateFormat.format(new Date()));
        userService.updateTime();
        JobExecutionException e2 = new JobExecutionException(new Exception());
        e2.setRefireImmediately(true);
        throw e2;
    }
}
