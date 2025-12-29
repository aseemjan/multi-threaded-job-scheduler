package com.scheduler.jobscheduler.persistence;

import java.util.List;
import com.scheduler.jobscheduler.domain.Job;
import com.scheduler.jobscheduler.domain.JobStatus;

public interface JobStore {
    void save(Job job);

    void update(Job job);

    List<Job> findByStatus(JobStatus status);

    List<Job> findAll();
}
