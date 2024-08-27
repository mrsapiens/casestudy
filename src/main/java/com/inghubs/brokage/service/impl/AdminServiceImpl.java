package com.inghubs.brokage.service.impl;

import com.inghubs.brokage.domain.bo.AdminBo;
import com.inghubs.brokage.domain.enumeration.Authority;
import com.inghubs.brokage.domain.exception.AdminAlreadyExists;
import com.inghubs.brokage.domain.exception.CustomerNotFoundException;
import com.inghubs.brokage.domain.mapper.AdminEntityMapper;
import com.inghubs.brokage.repository.AdminRepository;
import com.inghubs.brokage.service.AdminService;
import com.inghubs.brokage.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminEntityMapper adminMapper;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager manager;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsServiceImpl customUserDetailsService;

    @Override
    public AdminBo register(AdminBo adminBo) {

        AdminBo admin = retrieveAdmin(adminBo);

        if (admin != null) {
            throw new AdminAlreadyExists();
        }

        return persistAdmin(adminBo);
    }

    @Override
    public AdminBo login(AdminBo adminBo) {

        customUserDetailsService.setAuthority(Authority.ADMIN);

        try {

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(adminBo.getUsername(), adminBo.getPassword());
            manager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            AdminBo customer = retrieveAdmin(adminBo);

            if (customer == null) {
                throw new CustomerNotFoundException();
            }

            String token = jwtUtil.generateToken(adminBo.getUsername(), Authority.ADMIN);
            adminBo.setJwtToken(token);

            return adminBo;
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    private AdminBo initialize(AdminBo adminBo) {
        String encodedPassword = passwordEncoder.encode(adminBo.getPassword());
        adminBo.setPassword(encodedPassword);
        return adminBo;
    }

    private AdminBo retrieveAdmin(AdminBo adminBo) {
        return adminRepository.findByUsername(adminBo.getUsername()).map(adminMapper::convert).orElse(null);
    }

    private AdminBo persistAdmin(AdminBo adminBo) {
        adminRepository.save(adminMapper.convert(initialize(adminBo)));
        return adminBo;
    }

}
