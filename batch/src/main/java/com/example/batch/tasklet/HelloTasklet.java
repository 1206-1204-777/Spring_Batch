package com.example.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

//Taskletインターフェイスを実装
public class HelloTasklet implements Tasklet {
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
		}else {
			System.out.println("ポイントなし");
		}
		return RepeatStatus.FINISHED;
	}
}
