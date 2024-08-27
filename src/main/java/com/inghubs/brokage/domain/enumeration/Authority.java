package com.inghubs.brokage.domain.enumeration;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    ADMIN,
    INDIVIDUAL;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
