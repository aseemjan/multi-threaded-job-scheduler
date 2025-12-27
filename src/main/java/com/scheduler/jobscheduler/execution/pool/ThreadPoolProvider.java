package com.scheduler.jobscheduler.execution.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

public final class ThreadPoolProvider {

    private static final ExecutorService IMMEDIATE_POOL =
            Executors.newFixedThreadPool(10);

    private static final ScheduledExecutorService SCHEDULED_POOL =
            Executors.newScheduledThreadPool(5);

    private ThreadPoolProvider(){

    }

    public static ExecutorService immediatePool(){
        return IMMEDIATE_POOL;
    }

    public static ScheduledExecutorService scheduledPool(){
        return SCHEDULED_POOL;
    }

}
