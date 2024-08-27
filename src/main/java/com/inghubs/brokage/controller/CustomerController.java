package com.inghubs.brokage.controller;

import com.inghubs.brokage.domain.bo.CustomerBo;
import com.inghubs.brokage.domain.mapper.AssetMapper;
import com.inghubs.brokage.domain.mapper.CustomerMapper;
import com.inghubs.brokage.domain.model.Asset;
import com.inghubs.brokage.domain.model.Customer;
import com.inghubs.brokage.service.CustomerService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final AssetMapper assetMapper;

    @PostMapping("/{customerId}/deposit")
    public ResponseEntity<Customer> deposit(@NotNull @PathVariable("customerId") Long customerId,
                                            @Min(0) @RequestParam BigDecimal amount) {
        Customer customer = customerMapper.convert(customerService.deposit(customerId, amount));
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/{customerId}/withdraw")
    public ResponseEntity<Customer> withdraw(@NotNull @PathVariable("customerId") Long customerId,
                                             @Min(0) @RequestParam BigDecimal amount) {
        Customer customer = customerMapper.convert(customerService.withdraw(customerId, amount));
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/{customerId}/assets")
    public ResponseEntity<List<Asset>> listAssets(@NotNull @PathVariable("customerId") Long customerId) {
        List<Asset> customers = customerService.listAssets(customerId).stream().map(assetMapper::convert).toList();
        return ResponseEntity.ok(customers);
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> register(@NotNull @RequestBody Customer customer) {
        CustomerBo customerBo = customerService.register(customerMapper.convert(customer));
        return ResponseEntity.ok(customerMapper.convert(customerBo));
    }

    @PostMapping("/login")
    public ResponseEntity<Customer> login(@NotNull @RequestBody Customer customer) {
        CustomerBo customerBo = customerService.login(customerMapper.convert(customer));
        return ResponseEntity.ok(customerMapper.convert(customerBo));
    }
}
