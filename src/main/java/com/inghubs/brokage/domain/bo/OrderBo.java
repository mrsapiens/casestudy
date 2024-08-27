package com.inghubs.brokage.domain.bo;

import com.inghubs.brokage.domain.enumeration.OrderSide;
import com.inghubs.brokage.domain.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderBo {
    private Long id;
    private Long customerId;
    private String assetName;
    private OrderSide orderSide;
    private Integer size;
    private BigDecimal price;
    private OrderStatus status;
    private LocalDateTime createDate;
}
