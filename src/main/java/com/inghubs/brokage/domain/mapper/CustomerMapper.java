package com.inghubs.brokage.domain.mapper;

import com.inghubs.brokage.domain.bo.CustomerBo;
import com.inghubs.brokage.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CustomerMapper {

    @Mapping(target = "password", ignore = true, source = "password")
    Customer convert(CustomerBo customerBo);

    CustomerBo convert(Customer customer);
}
