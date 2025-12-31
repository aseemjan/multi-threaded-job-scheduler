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

@SpringBootApplication
public class JobSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobSchedulerApplication.class, args);

        JobStore jobStore = new InMemoryJobStore();
        JobScheduler jobScheduler = new JobScheduler(jobStore);

        SchedulerBootstrap bootstrap =
                new SchedulerBootstrap(jobStore, jobScheduler);

        bootstrap.start();
	}

    @Bean
    ApplicationRunner recoveryRunner(JobRecoveryService recoveryService) {
        return args -> recoveryService.recoverAndReschedule();
    }
}
