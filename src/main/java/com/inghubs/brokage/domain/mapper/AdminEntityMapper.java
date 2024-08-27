package com.inghubs.brokage.domain.mapper;

import com.inghubs.brokage.domain.bo.AdminBo;
import com.inghubs.brokage.domain.entity.AdminEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface AdminEntityMapper {

    AdminEntity convert(AdminBo assetBo);

    AdminBo convert(AdminEntity assetEntity);

}
