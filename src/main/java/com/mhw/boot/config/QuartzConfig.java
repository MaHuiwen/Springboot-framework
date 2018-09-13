package com.mhw.boot.config;

import com.mhw.boot.task.UserTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

/*    @Bean
    public JobDetail testTaskJob() {
        return JobBuilder
                .newJob(UserTask.class)
                .withIdentity("UserTask")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger testTaskTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/30 * * * * ?");
        return TriggerBuilder.newTrigger().forJob(testTaskJob())
                .withIdentity("testTaskTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }*/
}
