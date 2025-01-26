package com.example.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobScheduler {
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private Job jobProcess;
	@Autowired
	private Job DatabaseJob;

	//Taaskletを実行するメソッド
	@Scheduled(fixedRate = 1000)
	public void runTaskletJob() {
		try {
			JobParameters jobParameters = new JobParametersBuilder()
					.addLong("time", System.currentTimeMillis())
					.toJobParameters();
			jobLauncher.run(jobProcess, jobParameters);
		} catch (Exception e) {

		}
	}

	//データベースへの書き込みを実行するメソッド
	@Scheduled(cron = "0 * * * * ?")
	public void runDatabaseWriteJob() {
		try {
			jobLauncher.run(DatabaseJob, new JobParametersBuilder()
					.addLong("time", System.currentTimeMillis())
					.toJobParameters());
		} catch (Exception e) {

		}
	}
}
