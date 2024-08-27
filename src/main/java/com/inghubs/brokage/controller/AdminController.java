package com.inghubs.brokage.controller;

import com.inghubs.brokage.domain.bo.AdminBo;
import com.inghubs.brokage.domain.mapper.AdminMapper;
import com.inghubs.brokage.domain.model.Admin;
import com.inghubs.brokage.service.AdminService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final AdminMapper adminMapper;


    @PostMapping("/register")
    public ResponseEntity<Admin> register(@NotNull @RequestBody Admin admin) {
        AdminBo adminBo = adminService.register(adminMapper.convert(admin));
        return ResponseEntity.ok(adminMapper.convert(adminBo));
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> login(@NotNull @RequestBody Admin admin) {
        AdminBo adminBo = adminService.login(adminMapper.convert(admin));
        return ResponseEntity.ok(adminMapper.convert(adminBo));
    }
}
