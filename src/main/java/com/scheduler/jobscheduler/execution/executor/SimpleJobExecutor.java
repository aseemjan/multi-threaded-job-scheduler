package com.scheduler.jobscheduler.execution.executor;

import com.scheduler.jobscheduler.execution.pool.ThreadPoolProvider;

import java.util.concurrent.ExecutorService;

public class SimpleJobExecutor implements JobExecutor{

    private final ExecutorService executorservice;

    public SimpleJobExecutor(){
        this.executorservice = ThreadPoolProvider.immediatePool();
    }

    @Override
    public void execute(Runnable task) {
        executorservice.submit(task);
    }
}
