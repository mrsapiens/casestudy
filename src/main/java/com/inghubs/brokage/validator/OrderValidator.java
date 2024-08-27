package com.inghubs.brokage.validator;

import com.inghubs.brokage.domain.bo.CustomerBo;
import com.inghubs.brokage.domain.bo.OrderBo;
import com.inghubs.brokage.domain.enumeration.OrderSide;
import com.inghubs.brokage.domain.exception.InsufficientCustomerAsset;
import com.inghubs.brokage.domain.exception.InsufficientCustomerBalance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Component
public class OrderValidator {

    public void validateOrder(OrderBo orderBo, CustomerBo customerBo) {
        if (orderBo.getOrderSide() == OrderSide.SELL) {
            boolean isValid = customerBo.getAssets().stream().anyMatch(a -> orderBo.getAssetName().equals(a.getAssetName()) && a.getUsableSize() >= orderBo.getSize());
            if (!isValid) {
                throw new InsufficientCustomerAsset();
            }
        } else {
            BigDecimal totalCost = orderBo.getPrice().multiply(BigDecimal.valueOf(orderBo.getSize()));
            if (totalCost.compareTo(customerBo.getBalance()) > 0) {
                throw new InsufficientCustomerBalance();
            }
        }
    }
}
