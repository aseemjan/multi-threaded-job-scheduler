package com.scheduler.jobscheduler.execution.strategy;

import com.scheduler.jobscheduler.execution.executor.JobExecutor;

public class ImmediateExecutionStrategy implements ExecutionStrategy {

    private final JobExecutor jobExecutor;


    public ImmediateExecutionStrategy(JobExecutor jobExecutor){
        this.jobExecutor = jobExecutor;
    }
    @Override
    public void execute(Runnable task){
        jobExecutor.execute(task);
    }
}
