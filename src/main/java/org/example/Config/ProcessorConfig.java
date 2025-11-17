package org.example.Config;
import lombok.extern.slf4j.Slf4j;
import org.example.Dto.CustomerDto;
import org.example.Entity.Customer;
import org.example.Mapper.CustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ProcessorConfig {

    private final CustomerMapper mapper;
    private static final Logger log = LoggerFactory.getLogger(ProcessorConfig.class);

    public ProcessorConfig(CustomerMapper mapper) {
        this.mapper = mapper;
    }

    @Bean
    public ItemProcessor<CustomerDto, Customer> processor() {
        return dto -> {
            log.info("PROCESSING DTO = " + dto);

            // Here We are converting the DTO to the Entity beacuse we can not expose the direct Data base structure to the
            //API so we used the DTO to convert the DTO to Entity we used the Mapper here.

            Customer entity = mapper.toEntity(dto);
            log.info("MAPPED ENTITY = " + entity);

            return entity; // MUST NOT BE NULL
        };
    }
}
