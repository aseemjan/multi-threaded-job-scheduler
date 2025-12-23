package com.scheduler.jobscheduler.execution;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutionStrategy implements ExecutionStrategy{

    private final ScheduledExecutorService scheduledExecutorService;

    public ScheduledExecutionStrategy(ScheduledExecutorService scheduledExecutorService){
        this.scheduledExecutorService = scheduledExecutorService;
    }

    public void executeWithDelay(Runnable task, long delayMillis){
        scheduledExecutorService.schedule(task, delayMillis, TimeUnit.MILLISECONDS);
    }

    @Override
    public void execute(Runnable task){
        scheduledExecutorService.submit(task);
    }
}
