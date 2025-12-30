package com.scheduler.jobscheduler.execution.strategy;

import com.scheduler.jobscheduler.domain.Job;

public interface ExecutionStrategy {
    void execute(Job job,Runnable task);
}
