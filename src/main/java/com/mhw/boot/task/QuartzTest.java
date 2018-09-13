package com.mhw.boot.task;

import com.mhw.boot.service.serviceImp.HelloJobImpl;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {

   /* public static void main(String[] args) {
        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            //and start it off
            scheduler.start();

            //define the job and tie it to our HelloJob class
            JobDetail job = JobBuilder.newJob(HelloJobImpl.class)
                    .withIdentity("job1", "group1")
                    .build();

            scheduler.shutdown();
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }*/
}
