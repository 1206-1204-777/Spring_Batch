package com.example.batch.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;
//Batchのクラスであることを示す
@Configuration 
//Batch機能の有効化
@EnableBatchProcessing 
public class BatchConfig {
	private final JobBuilderFactory jobBbuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	//コンストラクタで必要なビルダーファクトリーを注入
	public BatchConfig(JobBuilderfactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
		this.jobBbuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		
	}
}