package com.inghubs.brokage.domain.entity;

import com.inghubs.brokage.domain.enumeration.OrderSide;
import com.inghubs.brokage.domain.enumeration.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @Column(name = "asset_name")
    private String assetName;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_side")
    private OrderSide orderSide;

    @Column(name = "size")
    private Integer size;

    @Column(name = "price")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "create_date")
    private LocalDateTime createDate;
}
