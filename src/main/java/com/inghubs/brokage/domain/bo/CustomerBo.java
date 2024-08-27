package com.inghubs.brokage.domain.bo;

import com.inghubs.brokage.domain.enumeration.Authority;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CustomerBo {

    private Long id;
    private List<AssetBo> assets;
    private List<OrderBo> orders;
    private Authority authority;
    private String username;
    private String password;
    private BigDecimal balance;
    private String jwtToken;

    public void increaseBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void decreaseBalance(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }
}
