package com.scheduler.jobscheduler.metrics;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class JobMetrics {

    private static final AtomicLong jobsCreated = new AtomicLong();
    private static final AtomicLong jobsCompleted = new AtomicLong();
    private static final AtomicLong jobsFailed = new AtomicLong();
    private static final AtomicInteger jobsRunning = new AtomicInteger();

    public static void incrementCreated() {
        jobsCreated.incrementAndGet();
    }

    public static void incrementCompleted() {
        jobsCompleted.incrementAndGet();
    }

    public static void incrementFailed() {
        jobsFailed.incrementAndGet();
    }

    public static void incrementRunning() {
        jobsRunning.incrementAndGet();
    }

    public static void decrementRunning() {
        jobsRunning.decrementAndGet();
    }

    // Optional (used later for debugging / health endpoints)
    public static long getJobsCreated() {
        return jobsCreated.get();
    }

    public static long getJobsCompleted() {
        return jobsCompleted.get();
    }

    public static long getJobsFailed() {
        return jobsFailed.get();
    }

    public static int getJobsRunning() {
        return jobsRunning.get();
    }
}
