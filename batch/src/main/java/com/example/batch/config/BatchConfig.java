package com.example.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.batch.tasklet.HelloTasklet;

// Spring Batchの設定クラスであることを示す
@Configuration
// Batch機能を有効化
@EnableBatchProcessing
public class BatchConfig {
	private final JobRepository jobRepository;
	private final PlatformTransactionManager transactionManager;
	
	//コンストラクタで必要なビルダーファクトリーを注入
	public BatchConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		this.jobRepository = jobRepository;
		this.transactionManager = transactionManager;
	}
	
	//stepの定義
	@Bean
	public Step stepProcess() {
		return new StepBuilder("stepProcess",jobRepository)
				.tasklet(new HelloTasklet(),transactionManager)
				.build();
	}
	
	//jobを定義
	@Bean
	public Job jobProcess() {
		return new JobBuilder("jobProcess",jobRepository)
				.start(stepProcess())
				.build();
	}
}
