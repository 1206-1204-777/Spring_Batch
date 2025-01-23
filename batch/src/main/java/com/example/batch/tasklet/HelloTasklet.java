package com.example.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
//Taskletインターフェイスを実装
public class HelloTasklet implements Tasklet{
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception{
		System.out.println("Hello, Batch");
		return RepeatStatus.FINISHED;
	}
}
