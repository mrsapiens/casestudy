package com.inghubs.brokage.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.inghubs.brokage.domain.enumeration.Authority;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Customer {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Asset> assets;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Order> orders;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Authority authority;

    private String username;

    private String password;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal balance;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String jwtToken;
}
