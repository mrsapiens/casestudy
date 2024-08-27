package com.inghubs.brokage.domain.mapper;

import com.inghubs.brokage.domain.bo.CustomerBo;
import com.inghubs.brokage.domain.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CustomerEntityMapper {

    @Mapping(target = "id", ignore = true, source = "id")
    CustomerEntity convert(CustomerBo customerBo);

    CustomerBo convert(CustomerEntity customerEntity);
}
