package com.inghubs.brokage.domain.mapper;

import com.inghubs.brokage.domain.bo.OrderBo;
import com.inghubs.brokage.domain.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface OrderEntityMapper {

    OrderEntity convert(OrderBo orderBo);

    @Mapping(target = "customerId", source = "customer.id")
    OrderBo convert(OrderEntity orderEntity);
}
