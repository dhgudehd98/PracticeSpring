package com.sh.updown.batch.job;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
//@EnableScheduling
public class SchedulingJobConfiguration {

    private final JobLauncher jobLauncher;
//    private final Job mySpringBatchJob;  // Spring Batch Job 주입
    private final Job chrolingJob;

//    @Scheduled(fixedDelay = 10000)
    public void scheduleJobLauncher() throws Exception {
        // 매 실행마다 고유한 JobParameters를 생성하여 새로운 JobInstance를 생성하도록 합니다.
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())  // 매 실행마다 고유한 파라미터 생성
                .addDate("runDate", new Date())  // 추가 고유 파라미터
                .addLong("run.id", System.currentTimeMillis()) // 새로 추가된 고유 ID
                .toJobParameters();

        log.info("Job 인스턴스: {}", chrolingJob);

        jobLauncher.run(chrolingJob, jobParameters);
    }
}
