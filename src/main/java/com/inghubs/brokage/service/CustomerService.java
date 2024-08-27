package com.inghubs.brokage.service;

import com.inghubs.brokage.domain.bo.AssetBo;
import com.inghubs.brokage.domain.bo.CustomerBo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface CustomerService {

    CustomerBo deposit(Long customerId, BigDecimal amount);

    CustomerBo withdraw(Long customerId, BigDecimal amount);

    List<AssetBo> listAssets(Long customerId);

    CustomerBo register(CustomerBo customerBo);

    CustomerBo login(CustomerBo customerBo);
}
