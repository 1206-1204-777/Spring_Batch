package com.example.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobScheduler {
	@Autowired
	private JobLauncher jobLaunchar;
	@Autowired
	private Job jobProcess;

	//10秒ごとに処理を行う
	@Scheduled(fixedRate = 10000)
	public void runjob() {
		try {
			jobLaunchar.run(jobProcess, new JobParametersBuilder()
					.addLong("time",System.currentTimeMillis())
			.toJobParameters());
			System.out.println("Hello");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
