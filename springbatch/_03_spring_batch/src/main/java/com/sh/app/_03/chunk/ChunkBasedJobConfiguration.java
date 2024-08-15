package com.sh.app._03.chunk;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ChunkBasedJobConfiguration {
    private final EntityManagerFactory entityManagerFactory;
    private int chunkSize = 5; // 트랜잭션 처리될 엔티티수

    @Bean
    @Primary // Job 빈이 여러개인 경우 우선순위 부여
    public Job chunkBasedJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("chunkBasedJob", jobRepository)
                .start(step)
                .build();
    }
    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) throws Exception {
        return new StepBuilder("step", jobRepository)
                .<User,User>chunk(chunkSize, platformTransactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<User> reader() throws Exception {
        return new JpaPagingItemReaderBuilder<User>()
                .name("UserJpaPagingItemReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString("FROM User u ORDER BY id ASC")
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<User, User> processor(){
        return (user) -> {
                user.setPoint(user.getPoint() + 1000);
                return user;
            };
    }

    @Bean
    @StepScope
    public ItemWriter<User> writer(){
        return new JpaItemWriterBuilder<User>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}