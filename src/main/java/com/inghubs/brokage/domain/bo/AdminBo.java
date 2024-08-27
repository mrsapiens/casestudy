package com.inghubs.brokage.domain.bo;

import com.inghubs.brokage.domain.enumeration.Authority;
import lombok.Data;

@Data
public class AdminBo {
    private Long id;
    private String username;
    private String password;
    private Authority authority;
    private String jwtToken;
}
