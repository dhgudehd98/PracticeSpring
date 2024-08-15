package com.sh.app._02.multiple.job.step;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Slf4j
public class MultipleJobStepConfiguration {
    @Bean
    public Job multipleJobStep(JobRepository jobRepository, Step step1, Step step2) {
        return new JobBuilder("multipleJobStep", jobRepository)
                .start(step1)
                .next(step2)
                .build();
    }
    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("step1", jobRepository)
                .tasklet(tasklet1(), platformTransactionManager).build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("step2", jobRepository)
                .tasklet(tasklet2(), platformTransactionManager).build();
    }

    /**
     * 하나의 Step당 하나의 tasklet 지정
     * @return
     */
    @Bean
    public Tasklet tasklet1(){
        return ((contribution, chunkContext) -> {
            log.info(">>>>> This is MultipleJobStep - Step1 - tasklet1");
            return RepeatStatus.FINISHED;
        });
    }
    @Bean
    public Tasklet tasklet2(){
        return ((contribution, chunkContext) -> {
            log.info(">>>>> This is MultipleJobStep - Step2 - tasklet2");
            return RepeatStatus.FINISHED;
        });
    }
}
