package com.scheduler.jobscheduler.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.Map;

import com.scheduler.jobscheduler.domain.Job;
import com.scheduler.jobscheduler.domain.JobStatus;


public class InMemoryJobStore implements JobStore{
    private final ConcurrentHashMap<String, Job> store = new ConcurrentHashMap<>();

    @Override
    public void save(Job job) {
        store.put(job.getId(), job);
    }

    @Override
    public void update(Job job) {
        store.put(job.getId(), job);
    }

    @Override
    public List<Job> findByStatus(List<JobStatus> statuses) {

        List<Job> result = new ArrayList<>();

        for (Job job : store.values()) {
            if (statuses.contains(job.getStatus())) {
                result.add(job);
            }
        }
        return result;
    }

    @Override
    public List<Job> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Job> findRecoverableJobs() {
        return findByStatus(
                List.of(
                        JobStatus.SCHEDULED,
                        JobStatus.RUNNING
                )
        );
    }

}
