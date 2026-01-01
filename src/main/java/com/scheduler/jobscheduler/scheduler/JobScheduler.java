package com.scheduler.jobscheduler.scheduler;

import com.scheduler.jobscheduler.domain.Job;
import com.scheduler.jobscheduler.execution.ExecutionTokenGenerator;
import com.scheduler.jobscheduler.execution.strategy.ExecutionStrategy;
import com.scheduler.jobscheduler.persistence.JobStore;

import com.scheduler.jobscheduler.metrics.JobMetrics;


public class JobScheduler {

    private final JobStore jobStore;

    public JobScheduler(JobStore jobStore) {
        this.jobStore = jobStore;
    }

    public void schedule(Job job){
        job.markScheduled();
        JobMetrics.incrementCreated();
        jobStore.save(job);

        System.out.println(
                "[JOB-SCHEDULED] jobId=" + job.getId() +
                        " type=" + job.getType()
        );

        ExecutionStrategy strategy =
                ExecutionStrategyFactory.getStrategy(job.getType());

        Runnable wrappedTask = () -> {
            String token = ExecutionTokenGenerator.generate();

            boolean acquired = job.tryAcquireExecution(token);
            if (!acquired) {
                System.out.println(
                        "[JOB-SKIPPED] jobId=" + job.getId() + " reason=already-running"
                );
                return;
            }

            try {
                job.markRunning();
                JobMetrics.incrementRunning();
                jobStore.update(job);

                System.out.println(
                        "[JOB-STARTED] jobId=" + job.getId() +
                                " thread=" + Thread.currentThread().getName()
                );

                strategy.execute(job, () -> {
                    // actual job logic
                });

                job.markCompleted();
                job.releaseExecution();          // ✅ RELEASE ON SUCCESS
                JobMetrics.decrementRunning();
                JobMetrics.incrementCompleted();
                jobStore.update(job);

                System.out.println(
                        "[JOB-COMPLETED] jobId=" + job.getId()
                );

            } catch (Exception ex) {
                job.markFailed();
                job.releaseExecution();          // ✅ RELEASE ON FAILURE
                JobMetrics.decrementRunning();
                JobMetrics.incrementFailed();
                jobStore.update(job);

                System.out.println(
                        "[JOB-FAILED] jobId=" + job.getId() +
                                " error=" + ex.getMessage()
                );
            }
        };

        wrappedTask.run();
    }
}
