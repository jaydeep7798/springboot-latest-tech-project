package org.example.Listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobCompletionListener implements JobExecutionListener {

    @Override
    public void afterJob(JobExecution jobExecution) {
       // log.info("JOB FINISHED with status: {}", jobExecution.getStatus());
    }
}