package com.scheduler.jobscheduler.domain;

import java.time.Instant;
import java.util.UUID;

public class Job {
    private final String id;
    private final JobType type;
    private JobStatus status;
    private final JobSchedule schedule;
    private final int priority;
    private final Instant createdAt;
    private String executionToken;


    public Job(JobType type, JobSchedule schedule, int priority){
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.schedule = schedule;
        this.priority = priority;
        this.status = JobStatus.CREATED;
        this.createdAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public JobType getType() {
        return type;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void markScheduled() {
        this.status = JobStatus.SCHEDULED;
    }

    public void markRunning() {
        this.status = JobStatus.RUNNING;
    }

    public void markCompleted() {
        this.status = JobStatus.COMPLETED;
    }

    public void markFailed() {
        this.status = JobStatus.FAILED;
    }

    public JobSchedule getSchedule() {
        return schedule;
    }

    public int getPriority() {
        return priority;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public boolean isTerminal(){
        return status == JobStatus.COMPLETED || status == JobStatus.FAILED;
    }

    public synchronized boolean tryAcquireExecution(String token) {
        if (this.executionToken != null) {
            return false;
        }
        this.executionToken = token;
        return true;
    }

    public synchronized void releaseExecution() {
        this.executionToken = null;
    }

    public String getExecutionToken() {
        return executionToken;
    }
}
