package com.inghubs.brokage.validator;

import com.inghubs.brokage.domain.bo.CustomerBo;
import com.inghubs.brokage.domain.exception.InsufficientCustomerBalance;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CustomerValidator {

    public void validateWithdraw(CustomerBo customerBo, BigDecimal amount) {
        if (amount.compareTo(customerBo.getBalance()) > 0) {
            throw new InsufficientCustomerBalance();
        }
    }
}
