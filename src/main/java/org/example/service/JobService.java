package org.example.service;


import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    private final JobLauncher jobLauncher;  // ✅ Spring will inject this
    private final Job job;                  // ✅ Your batch job bean

    public JobService(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    public void runJob(String filePath) {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("filePath", filePath)
                    .addLong("startAt", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(job, jobParameters);// it will this run this job

        } catch (Exception e) {
            throw new RuntimeException("Failed to execute job", e);
        }
    }
}

