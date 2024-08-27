package com.inghubs.brokage.domain.mapper;

import com.inghubs.brokage.domain.bo.AssetBo;
import com.inghubs.brokage.domain.entity.AssetEntity;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface AssetEntityMapper {

    AssetEntity convert(AssetBo assetBo);

    AssetBo convert(AssetEntity assetEntity);

}
