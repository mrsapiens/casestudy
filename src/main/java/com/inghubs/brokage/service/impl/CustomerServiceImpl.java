package com.inghubs.brokage.service.impl;

import com.inghubs.brokage.domain.bo.AssetBo;
import com.inghubs.brokage.domain.bo.CustomerBo;
import com.inghubs.brokage.domain.entity.CustomerEntity;
import com.inghubs.brokage.domain.enumeration.Authority;
import com.inghubs.brokage.domain.exception.CustomerAlreadyExists;
import com.inghubs.brokage.domain.exception.CustomerNotFoundException;
import com.inghubs.brokage.domain.mapper.CustomerEntityMapper;
import com.inghubs.brokage.repository.CustomerRepository;
import com.inghubs.brokage.service.CustomerService;
import com.inghubs.brokage.util.JwtUtil;
import com.inghubs.brokage.validator.CustomerValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerEntityMapper customerMapper;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager manager;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsServiceImpl customUserDetailsService;
    private final CustomerValidator customerValidator;

    @Override
    public CustomerBo deposit(Long customerId, BigDecimal amount) {

        CustomerBo customerBo = retrieveCustomer(customerId).orElseThrow(CustomerNotFoundException::new);

        customerBo.increaseBalance(amount);

        return persistCustomer(customerBo);

    }

    @Override
    public CustomerBo withdraw(Long customerId, BigDecimal amount) {

        CustomerBo customerBo = retrieveCustomer(customerId).orElseThrow(CustomerNotFoundException::new);

        customerValidator.validateWithdraw(customerBo, amount);

        customerBo.decreaseBalance(amount);

        return persistCustomer(customerBo);
    }

    @Override
    public List<AssetBo> listAssets(Long customerId) {

        CustomerBo customerBo = retrieveCustomer(customerId).orElseThrow(CustomerNotFoundException::new);

        return customerBo.getAssets();
    }


    @Override
    public CustomerBo register(CustomerBo customerBo) {

        retrieveCustomer(customerBo.getUsername()).ifPresent(e -> {
            throw new CustomerAlreadyExists();
        });

        initialize(customerBo);

        return persistCustomer(customerBo);
    }


    @Override
    public CustomerBo login(CustomerBo customerBo) {

        customUserDetailsService.setAuthority(Authority.INDIVIDUAL);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(customerBo.getUsername(), customerBo.getPassword());
        manager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        retrieveCustomer(customerBo.getUsername()).orElseThrow(CustomerNotFoundException::new);

        String token = jwtUtil.generateToken(customerBo.getUsername(), customerBo.getAuthority());
        customerBo.setJwtToken(token);

        return customerBo;

    }

    private void initialize(CustomerBo customerBo) {
        String encodedPassword = passwordEncoder.encode(customerBo.getPassword());
        customerBo.setPassword(encodedPassword);
        customerBo.setAuthority(Authority.INDIVIDUAL);
    }

    private Optional<CustomerBo> retrieveCustomer(Long customerId) {
        return customerRepository.findById(customerId).map(customerMapper::convert);
    }

    private Optional<CustomerBo> retrieveCustomer(String userName) {
        return customerRepository.findByUsername(userName).map(customerMapper::convert);
    }

    private CustomerBo persistCustomer(CustomerBo customerBo) {
        CustomerEntity customerEntity = customerRepository.save(customerMapper.convert(customerBo));
        return customerMapper.convert(customerEntity);
    }

}
