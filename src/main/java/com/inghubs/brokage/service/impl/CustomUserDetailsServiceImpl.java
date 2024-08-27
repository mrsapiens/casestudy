package com.inghubs.brokage.service.impl;

import com.inghubs.brokage.domain.entity.AdminEntity;
import com.inghubs.brokage.domain.entity.CustomerEntity;
import com.inghubs.brokage.domain.enumeration.Authority;
import com.inghubs.brokage.domain.exception.CustomerNotFoundException;
import com.inghubs.brokage.repository.AdminRepository;
import com.inghubs.brokage.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;

    private Authority authority;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (authority == Authority.ADMIN) {
            AdminEntity adminEntity = adminRepository.findByUsername(username).orElseThrow(CustomerNotFoundException::new);
            return new org.springframework.security.core.userdetails.User(
                    adminEntity.getUsername(),
                    adminEntity.getPassword(),
                    Collections.singletonList(adminEntity.getAuthority()));
        } else {
            CustomerEntity customerEntity = customerRepository.findByUsername(username).orElseThrow(CustomerNotFoundException::new);
            return new org.springframework.security.core.userdetails.User(
                    customerEntity.getUsername(),
                    customerEntity.getPassword(),
                    Collections.singletonList(customerEntity.getAuthority()));
        }
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}
