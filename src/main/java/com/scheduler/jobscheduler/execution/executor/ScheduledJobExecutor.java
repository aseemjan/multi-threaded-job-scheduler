package com.scheduler.jobscheduler.execution.executor;

import com.scheduler.jobscheduler.execution.pool.ThreadPoolProvider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class ScheduledJobExecutor implements JobExecutor{

    private final ExecutorService executorService;

    public ScheduledJobExecutor(){
        this.executorService = ThreadPoolProvider.scheduledPool();
    }

    @Override
    public void execute(Runnable task){
        executorService.submit(task);
    }
}
