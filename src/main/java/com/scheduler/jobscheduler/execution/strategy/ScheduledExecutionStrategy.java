package com.scheduler.jobscheduler.execution.strategy;

import com.scheduler.jobscheduler.domain.Job;
import com.scheduler.jobscheduler.execution.executor.ScheduledJobExecutor;

import com.scheduler.jobscheduler.execution.executor.JobExecutor;

public class ScheduledExecutionStrategy implements ExecutionStrategy{

    private final ScheduledJobExecutor jobExecutor;

    public ScheduledExecutionStrategy(ScheduledJobExecutor jobExecutor) {
        this.jobExecutor = jobExecutor;
    }

    @Override
    public void execute(Job job, Runnable task) {
        long delayMillis = job.getSchedule().getDelayMillis();
        jobExecutor.executeWithDelay(task, delayMillis);
    }
}
