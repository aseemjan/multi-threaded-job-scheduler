package com.scheduler.jobscheduler.execution;

import java.util.UUID;

public final class ExecutionTokenGenerator {

    private ExecutionTokenGenerator() {

    }

    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
