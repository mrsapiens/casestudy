package com.inghubs.brokage.domain.entity;

import com.inghubs.brokage.domain.enumeration.Authority;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "admin_user")
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority", nullable = false)
    private Authority authority;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;
}
