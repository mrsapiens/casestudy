package com.inghubs.brokage.domain.mapper;

import com.inghubs.brokage.domain.bo.AdminBo;
import com.inghubs.brokage.domain.bo.OrderBo;
import com.inghubs.brokage.domain.model.Admin;
import com.inghubs.brokage.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface AdminMapper {

    @Mapping(target = "password", ignore = true)
    Admin convert(AdminBo orderBo);

    AdminBo convert(Admin order);
}
