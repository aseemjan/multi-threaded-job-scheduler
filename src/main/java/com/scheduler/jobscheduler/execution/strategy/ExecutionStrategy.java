package com.scheduler.jobscheduler.execution.strategy;

public interface ExecutionStrategy {
    void execute(Runnable task);
}
