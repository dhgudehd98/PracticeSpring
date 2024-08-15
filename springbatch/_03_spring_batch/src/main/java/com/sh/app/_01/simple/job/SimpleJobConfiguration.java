package com.sh.app._01.simple.job;

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

@Slf4j
@Configuration
public class SimpleJobConfiguration {
    @Bean
    public Job simpleJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("simpleJob", jobRepository)
                .start(step)
                .build();
    }
    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("step", jobRepository)
                .tasklet(tasklet(), platformTransactionManager).build();
    }
    /**
     * 하나의 Step당 하나의 tasklet 지정
     * @return
     */
    @Bean
    public Tasklet tasklet(){
        return ((contribution, chunkContext) -> {
            log.info(">>>>> This is SimpleJob - Step - Tasklet");
            return RepeatStatus.FINISHED;
        });
    }
}