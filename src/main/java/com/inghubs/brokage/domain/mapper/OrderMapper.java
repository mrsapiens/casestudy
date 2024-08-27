package com.inghubs.brokage.domain.mapper;

import com.inghubs.brokage.domain.bo.OrderBo;
import com.inghubs.brokage.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface OrderMapper {

    Order convert(OrderBo orderBo);

    OrderBo convert(Order order);
}
