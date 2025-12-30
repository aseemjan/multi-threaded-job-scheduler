package com.scheduler.jobscheduler.scheduler;

import com.scheduler.jobscheduler.domain.Job;
import com.scheduler.jobscheduler.domain.JobStatus;
import com.scheduler.jobscheduler.execution.strategy.ExecutionStrategy;
import com.scheduler.jobscheduler.persistence.JobStore;

import java.util.List;

public class JobScheduler {

    private final JobStore jobStore;

    public JobScheduler(JobStore jobStore) {
        this.jobStore = jobStore;
    }

    public void schedule(Job job){
        job.markScheduled();
        jobStore.save(job);

        ExecutionStrategy strategy =
                ExecutionStrategyFactory.getStrategy(job.getType());

        Runnable wrappedTask = () -> {
            try {
                job.markRunning();
                jobStore.update(job);

                strategy.execute(() -> {
                    // actual job logic will go here later
                });
                job.markCompleted();
                jobStore.update(job);
            } catch (Exception ex) {
                job.markFailed();
                jobStore.update(job);
            }
        };

        wrappedTask.run();
    }
}
