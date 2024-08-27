package com.inghubs.brokage.service.impl;

import com.inghubs.brokage.domain.bo.AssetBo;
import com.inghubs.brokage.domain.bo.CustomerBo;
import com.inghubs.brokage.domain.entity.AssetEntity;
import com.inghubs.brokage.domain.entity.CustomerEntity;
import com.inghubs.brokage.domain.exception.AssetAlreadyExists;
import com.inghubs.brokage.domain.exception.CustomerNotFoundException;
import com.inghubs.brokage.domain.mapper.AssetEntityMapper;
import com.inghubs.brokage.domain.mapper.CustomerEntityMapper;
import com.inghubs.brokage.repository.AssetRepository;
import com.inghubs.brokage.repository.CustomerRepository;
import com.inghubs.brokage.service.AssetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final AssetEntityMapper assetMapper;
    private final CustomerRepository customerRepository;
    private final CustomerEntityMapper customerMapper;

    @Override
    public AssetBo createAsset(AssetBo assetBo) {

        initialize(assetBo);

        Long customerId = assetBo.getCustomerBo().getId();
        CustomerEntity customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }

        CustomerBo customerBo = customerMapper.convert(customer);
        boolean assetExists = customerBo.getAssets().stream().anyMatch(a -> a.getAssetName().equals(assetBo.getAssetName()));
        if (assetExists) {
            throw new AssetAlreadyExists();
        }

        AssetEntity entity = assetRepository.findByAssetName(assetBo.getAssetName()).orElse(null);
        if (entity != null) {
            throw new AssetAlreadyExists();
        }

        AssetEntity assetEntity = assetMapper.convert(assetBo);
        assetEntity.setCustomer(customer);

        return assetMapper.convert(assetRepository.save(assetEntity));
    }

    private static void initialize(AssetBo assetBo) {
        assetBo.setUsableSize(assetBo.getSize());
    }
}
