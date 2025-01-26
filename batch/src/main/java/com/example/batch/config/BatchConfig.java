package com.example.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.batch.tasklet.DatabaseWriteTasklet;
import com.example.batch.tasklet.HelloTasklet;

// Spring Batchの設定クラスであることを示す
@Configuration
// Batch機能を有効化
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig {
	private final JobRepository jobRepository;
	private final PlatformTransactionManager transactionManager;
	private final JobLauncher jobLauncher;
	private final HelloTasklet helloTasklet;
	private final DatabaseWriteTasklet databaseTasklet;
	
	//コンストラクタで必要なビルダーファクトリーを注入
	public BatchConfig(HelloTasklet helloTasklet,JobRepository jobRepository, PlatformTransactionManager transactionManager,JobLauncher jobLauncher
			,DatabaseWriteTasklet databaseWriteTasklet) {
		this.jobRepository = jobRepository;
		this.transactionManager = transactionManager;
		this.jobLauncher = jobLauncher;
		this.helloTasklet = helloTasklet;
		this.databaseTasklet = databaseWriteTasklet;	
	}
	
	//Taskletstepの定義
	@Bean
	public Step Taskletstep() {
		return new StepBuilder("Taskletstep",jobRepository)
				.tasklet(helloTasklet,transactionManager)
				.build();
	}
	
	//Taskletjobを定義
	@Bean
	public Job Taskletjob() {
		return new JobBuilder("Taskletjob",jobRepository)
				.start(Taskletstep())
				.build();
	}
	@Bean
	public Step Databasestep() {
		return new StepBuilder("Databasestep",jobRepository)
				.tasklet(databaseTasklet,transactionManager)
				.build();
	}

}
