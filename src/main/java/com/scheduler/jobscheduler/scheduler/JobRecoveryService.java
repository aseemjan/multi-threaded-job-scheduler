package com.scheduler.jobscheduler.scheduler;

import com.scheduler.jobscheduler.domain.Job;
import com.scheduler.jobscheduler.domain.JobStatus;
import com.scheduler.jobscheduler.persistence.JobStore;

import java.util.List;

public class JobRecoveryService {

    private final JobStore jobStore;
    private final JobScheduler jobScheduler;

    public JobRecoveryService(JobStore jobStore, JobScheduler jobScheduler) {
        this.jobStore = jobStore;
        this.jobScheduler = jobScheduler;
    }

    public void recoverAndReschedule() {

        List<Job> recoverableJobs = jobStore.findByStatus(
                List.of(JobStatus.SCHEDULED, JobStatus.RUNNING)
        );

        for (Job job : recoverableJobs) {
            jobScheduler.schedule(job);
        }
    }
}
