package com.scheduler.jobscheduler.domain;

import java.time.Instant;

public class JobSchedule {
    private final Instant executionTime;
    private final Long delayMillis;
    private final Long intervalMillis;

    private JobSchedule(Instant executionTime, Long delayMillis, Long intervalMillis){
        this.executionTime = executionTime;
        this.delayMillis = delayMillis;
        this.intervalMillis = intervalMillis;
    }

    public static JobSchedule oneTime(Instant executionTime){
        return new JobSchedule(executionTime, null, null);
    }

    public static JobSchedule delayed(Long delayMillis){
        return new JobSchedule(null, delayMillis, null);
    }

    public static JobSchedule recurring(Long intervalMillis){
        return new JobSchedule(null, null, intervalMillis);
    }

    public Instant getExecutionTime(){
        return executionTime;
    }

    public Long getDelayMillis(){
        return delayMillis;
    }

    public Long getIntervalMillis(){
        return intervalMillis;
    }
}
