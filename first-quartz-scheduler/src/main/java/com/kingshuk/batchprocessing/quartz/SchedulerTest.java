package com.kingshuk.batchprocessing.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerTest {

    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory factory = new StdSchedulerFactory();

        Scheduler scheduler = factory.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(TheActualJob.class)
                .withIdentity("myJob1", "King's Jobs")
                .usingJobData("Kingshuk says", "Hello World")
                .build();


        Trigger myTrigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "myTriggerGroup")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(30)
                        .repeatForever()
                        .withMisfireHandlingInstructionFireNow())
                //.forJob(jobDetail)
                .build();

        scheduler.start();

        scheduler.scheduleJob(jobDetail, myTrigger);

    }
}
