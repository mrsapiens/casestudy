package com.inghubs.brokage.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Asset")
public class AssetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @Column(name = "asset_name")
    private String assetName;

    @Column(name = "usable_size")
    private Integer usableSize;

    @Column(name = "asset_size")
    private Integer size;
}
