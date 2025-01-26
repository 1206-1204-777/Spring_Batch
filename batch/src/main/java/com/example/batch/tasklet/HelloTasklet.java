package com.example.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.example.batch.service.TaskletResultService;

//Taskletインターフェイスを実装
@Component
public class HelloTasklet implements Tasklet {
	private final TaskletResultService taskletResultService;
	public HelloTasklet(TaskletResultService taskletResultService) {
		this.taskletResultService = taskletResultService;
	}
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		int num = (int) (Math.random() * 2500) + 1;
		int number = num * 2;
		System.out.println(number);
		if (number >= 2500 && number <= 4000) {

			int point = number / 6;
			if (point >= 500) {
				System.out.println(point + "ポイント");
			}
		}else if(number<= 500){
			System.out.println("ポイントなし");
		}else {
			System.out.println("No point");
		}
		return RepeatStatus.FINISHED;
	}
}
