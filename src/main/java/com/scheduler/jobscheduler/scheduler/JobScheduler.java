package com.scheduler.jobscheduler.scheduler;

import com.scheduler.jobscheduler.domain.Job;
import com.scheduler.jobscheduler.execution.strategy.ExecutionStrategy;

public class JobScheduler {

    public void schedule(Job job){
        job.markScheduled();

        ExecutionStrategy strategy =
                ExecutionStrategyFactory.getStrategy(job.getType());

        Runnable wrappedTask = () -> {
            try {
                job.markRunning();
                strategy.execute(() -> {
                    // actual job logic will go here later
                });
                job.markCompleted();
            } catch (Exception ex) {
                job.markFailed();
            }
        };

        wrappedTask.run();
    }
}
