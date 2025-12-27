package com.scheduler.jobscheduler.execution.executor;

public interface JobExecutor {
    void execute(Runnable task);
}
