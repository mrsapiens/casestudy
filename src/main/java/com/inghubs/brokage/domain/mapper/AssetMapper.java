package com.inghubs.brokage.domain.mapper;

import com.inghubs.brokage.domain.bo.AssetBo;
import com.inghubs.brokage.domain.model.Asset;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface AssetMapper {


    Asset convert(AssetBo assetBo);

    @Mapping(target = "customerBo.id", source = "customerId")
    AssetBo convert(Asset asset);

}
