package com.scheduler.jobscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.scheduler.jobscheduler.scheduler.JobRecoveryService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JobSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobSchedulerApplication.class, args);
	}

    @Bean
    ApplicationRunner recoveryRunner(JobRecoveryService recoveryService) {
        return args -> recoveryService.recoverAndReschedule();
    }
}
