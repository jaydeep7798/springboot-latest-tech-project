package org.example.Config;

import org.example.Dto.CustomerDto;
import org.example.Entity.Customer;
import org.example.Listener.JobCompletionListener;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing // EnableBatchProcessing is a Spring Batch annotation that activates and configures the entire Spring Batch infrastructure automatically.
public class BatchConfig {
    //BatchConfig is a configuration class where you define everything required for a Spring Batch job:

    private final JobCompletionListener jobListener;

    public BatchConfig(JobCompletionListener jobListener) {
        this.jobListener = jobListener;
    }

    @Bean
    public Job customerJob(JobRepository jobRepository, Step step) { // here we are creating a job which return the Job
        return new JobBuilder("customerJob", jobRepository)
                .incrementer(new RunIdIncrementer())// crate new Job
                .listener(jobListener) // listener will be active before and after job
                .start(step) // step will contain reader , process , writer
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository,
                     PlatformTransactionManager transactionManager,
                     FlatFileItemReader<CustomerDto> reader,
                     ItemProcessor<CustomerDto, Customer> processor,
                     ItemWriter<Customer> writer) {

        return new StepBuilder("csv-step", jobRepository)
                .<CustomerDto, Customer>chunk(100, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                //.retryLimit(3)
                //.retry(Exception.class)         // ✅ Retry for all exceptions
                .skipLimit(10)
                .skip(Exception.class)          // ✅ Specify which exceptions to skip
                .build();
    }
}
