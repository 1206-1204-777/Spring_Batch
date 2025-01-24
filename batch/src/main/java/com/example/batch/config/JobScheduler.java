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

	//1分ごとに処理を行う
	@Scheduled(fixedRate = 1000)
	public void runjob() {
		try {
			jobLaunchar.run(jobProcess, new JobParametersBuilder()
					.addLong("time",System.currentTimeMillis())
			.toJobParameters());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
