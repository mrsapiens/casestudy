package com.inghubs.brokage.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inghubs.brokage.domain.enumeration.OrderSide;
import com.inghubs.brokage.domain.enumeration.OrderStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Order {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Customer id is mandatory")
    private Long customerId;

    @NotBlank(message = "Asset name is mandatory")
    private String assetName;

    @NotBlank(message = "Order side is mandatory")
    private OrderSide orderSide;

    @Min(value = 0, message = "Size should be bigger than 0")
    private Integer size;

    @Min(value = 0, message = "Price should be bigger than 0")
    private BigDecimal price;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OrderStatus status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createDate;
}
