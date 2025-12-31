package com.scheduler.jobscheduler.recovery;

import com.scheduler.jobscheduler.domain.Job;
import com.scheduler.jobscheduler.domain.JobStatus;
import com.scheduler.jobscheduler.persistence.JobStore;
import com.scheduler.jobscheduler.scheduler.JobScheduler;

import java.util.List;

public class JobRecoveryService {

    private final JobStore jobStore;
    private final JobScheduler jobScheduler;

    public JobRecoveryService(JobStore jobStore, JobScheduler jobScheduler) {
        this.jobStore = jobStore;
        this.jobScheduler = jobScheduler;
    }

    public void recoverJobs() {
        List<Job> jobs = jobStore.findRecoverableJobs();

        for (Job job : jobs) {
            if (job.getStatus() == JobStatus.RUNNING) {
                job.markScheduled();
                jobStore.update(job);
            }

            jobScheduler.schedule(job);
        }
    }
}
