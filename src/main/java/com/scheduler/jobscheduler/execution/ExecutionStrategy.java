package com.scheduler.jobscheduler.execution;

public interface ExecutionStrategy {
    void execute(Runnable task);
}
