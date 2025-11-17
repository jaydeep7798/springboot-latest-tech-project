package org.example.Config;

import lombok.RequiredArgsConstructor;
import org.example.Entity.Customer;
import org.example.Repository.CustomerRepository;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WriterConfig {

    private final CustomerRepository repository;

    public WriterConfig(CustomerRepository repository) {
        this.repository = repository;
    }

    @Bean
    public RepositoryItemWriter<Customer> writer() {
        RepositoryItemWriter<Customer> writer = new RepositoryItemWriter<>();
        writer.setRepository(repository);
        writer.setMethodName("save");
        return writer;
    }
}
