package com.scheduler.jobscheduler.execution;

import java.util.UUID;

public final class ExecutionTokenGenerator {

    private ExecutionTokenGenerator() {
        // utility class
    }

    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
