package com.scheduler.jobscheduler.execution.strategy;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.scheduler.jobscheduler.execution.executor.JobExecutor;

public class ScheduledExecutionStrategy implements ExecutionStrategy{

    private final JobExecutor jobExecutor;

    public ScheduledExecutionStrategy(JobExecutor jobExecutor) {
        this.jobExecutor = jobExecutor;
    }

    @Override
    public void execute(Runnable task) {
        jobExecutor.execute(task);
    }
}
