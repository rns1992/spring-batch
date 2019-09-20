package com.example.csv2json.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BatchStatusListener extends JobExecutionListenerSupport {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        super.beforeJob(jobExecution);
        log.info(">> Starting job execution");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        super.afterJob(jobExecution);
        log.info(">> Job executed");
    }
}
