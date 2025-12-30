package com.scheduler.jobscheduler.execution.executor;

import com.scheduler.jobscheduler.execution.pool.ThreadPoolProvider;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledJobExecutor implements JobExecutor{

    private final ScheduledExecutorService scheduledExecutorService;

    public ScheduledJobExecutor() {
        this.scheduledExecutorService = ThreadPoolProvider.scheduledPool();
    }

    @Override
    public void execute(Runnable task){
        scheduledExecutorService.submit(task);
    }

    public void executeWithDelay(Runnable task, long delayMillis) {
        scheduledExecutorService.schedule(task, delayMillis, TimeUnit.MILLISECONDS);
    }
}
