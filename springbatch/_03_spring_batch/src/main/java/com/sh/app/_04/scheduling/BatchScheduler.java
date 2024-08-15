package com.sh.app._04.scheduling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class BatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job job;

    /**
     * <pre>
     * cron속성
     * - 초 분 시 일 월 요일
     * - 요일(0~7) : 일요일(0, 7), 월요일(1)~토요일(6)
     *
     * 특수문자
     * - * : 모든 값을 뜻합니다.
     * - ? : 특정한 값이 없음을 뜻합니다.
     * - - : 범위를 뜻합니다.
     * - , : 특별한 값일 때만 동작
     * - / : 시작시간 / 단위
     * - L : 일에서 사용하면 마지막 일, 요일에서는 마지막 요일(토요일)
     * - W : 가장 가까운 평일
     * - # : 몇째 주의 무슨 요일을 표현
     * </pre>
     */
    @Scheduled(cron= "0/30 * * * * *")
    public void runJob() {
        JobParameters jobParameters = new JobParametersBuilder()
                        .addLong("currentTime", System.currentTimeMillis())
                        .toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}