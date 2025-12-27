package com.scheduler.jobscheduler.scheduler;

import com.scheduler.jobscheduler.domain.JobType;
import com.scheduler.jobscheduler.execution.executor.ScheduledJobExecutor;
import com.scheduler.jobscheduler.execution.executor.SimpleJobExecutor;
import com.scheduler.jobscheduler.execution.strategy.ExecutionStrategy;
import com.scheduler.jobscheduler.execution.strategy.ImmediateExecutionStrategy;
import com.scheduler.jobscheduler.execution.strategy.ScheduledExecutionStrategy;


public class ExecutionStrategyFactory {
    private ExecutionStrategyFactory() {

    }

    public static ExecutionStrategy getStrategy(JobType jobType) {

        switch (jobType) {

            case ONETIME:
            case PRIORITY:
                return new ImmediateExecutionStrategy(
                        new SimpleJobExecutor()
                );

            case DELAYED:
            case RECURRING:
                return new ScheduledExecutionStrategy(
                        new ScheduledJobExecutor()
                );

            default:
                throw new IllegalArgumentException(
                        "Unsupported job type: " + jobType
                );
        }
    }
}
