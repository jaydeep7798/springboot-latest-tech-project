package org.example.Mapper;


import org.example.Dto.CustomerDto;
import org.example.Entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toEntity(CustomerDto dto);
}
