package com.scheduler.jobscheduler.bootstrap;

import com.scheduler.jobscheduler.persistence.JobStore;
import com.scheduler.jobscheduler.recovery.JobRecoveryService;
import com.scheduler.jobscheduler.scheduler.JobScheduler;

public class SchedulerBootstrap {

    private final JobRecoveryService jobRecoveryService;

    public SchedulerBootstrap(JobStore jobStore, JobScheduler jobScheduler) {
        this.jobRecoveryService = new JobRecoveryService(jobStore, jobScheduler);
    }

    public void start() {
        jobRecoveryService.recoverJobs();
    }
}
