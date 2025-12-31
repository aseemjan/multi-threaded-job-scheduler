package com.scheduler.jobscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.scheduler.jobscheduler.scheduler.JobRecoveryService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import com.scheduler.jobscheduler.bootstrap.SchedulerBootstrap;
import com.scheduler.jobscheduler.persistence.InMemoryJobStore;
import com.scheduler.jobscheduler.persistence.JobStore;
import com.scheduler.jobscheduler.scheduler.JobScheduler;

import com.scheduler.jobscheduler.domain.Job;
import com.scheduler.jobscheduler.domain.JobType;

import com.scheduler.jobscheduler.domain.JobSchedule;
import com.scheduler.jobscheduler.domain.Job;
import com.scheduler.jobscheduler.domain.JobType;

import java.time.Instant;


@SpringBootApplication
public class JobSchedulerApplication {

	public static void main(String[] args) {
        JobStore jobStore = new InMemoryJobStore();
        JobScheduler jobScheduler = new JobScheduler(jobStore);

        SchedulerBootstrap bootstrap =
                new SchedulerBootstrap(jobStore, jobScheduler);
        bootstrap.start();

        JobSchedule schedule = JobSchedule.oneTime(Instant.now());

        Job job = new Job(
                JobType.ONETIME,
                schedule,
                1
        );

        jobScheduler.schedule(job);
    }

    @Bean
    ApplicationRunner recoveryRunner(JobRecoveryService recoveryService) {
        return args -> recoveryService.recoverAndReschedule();
    }
}
