package org.example.Config;


import org.example.Dto.CustomerDto;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ReaderConfig {

    @Bean
    @StepScope
    public FlatFileItemReader<CustomerDto> customerReader(
            @Value("#{jobParameters['filePath']}") String filePath) { // It will Fetch the csv file from the path

        System.out.println(">>> Reader received filePath = " + filePath);

        return new FlatFileItemReaderBuilder<CustomerDto>()
                .name("customerCsvReader")
                .resource(new FileSystemResource(filePath))
                .delimited()
                .names("id", "name", "email")
                .targetType(CustomerDto.class)
                .build();
    }

}
