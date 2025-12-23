package com.scheduler.jobscheduler.execution;

import java.util.concurrent.ExecutorService;

public class ImmediateExecutionStrategy implements ExecutionStrategy {
    private final ExecutorService executorService;

    public ImmediateExecutionStrategy(ExecutorService executorService){
        this.executorService = executorService;
    }
    @Override
    public void execute(Runnable task){
        executorService.submit(task);
    }
}
