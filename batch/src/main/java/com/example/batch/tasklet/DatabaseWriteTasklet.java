	package com.example.batch.tasklet;
	
	import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.example.batch.service.TaskletResultService;
	@Component
	public class DatabaseWriteTasklet implements Tasklet {
		private TaskletResultService taskletResultService;
	
		@Override
		public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
			// TODO 自動生成されたメソッド・スタブ
			List<Integer> resultsToWrite = taskletResultService.getAndClearResults();
			if(!resultsToWrite.isEmpty()) {
				System.out.print("Database in");
			}else {
				System.out.print("Error");
			}
			return RepeatStatus.FINISHED;
		}
	
	}
