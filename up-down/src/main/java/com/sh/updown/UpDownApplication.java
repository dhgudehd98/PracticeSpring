package com.sh.updown;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
//@EnableScheduling
public class UpDownApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpDownApplication.class, args);
    }

}
