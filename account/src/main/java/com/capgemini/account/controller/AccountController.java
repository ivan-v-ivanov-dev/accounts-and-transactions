package com.capgemini.account.controller;

import com.capgemini.account.service.contract.AccountService;
import com.capgemini.models.dto.CustomerDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/{customerId}")
    public ResponseEntity<Long> create(@RequestParam("customerId") Long customerId) {
        return ResponseEntity.ok(accountService.create(customerId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<CustomerDto> findByCustomerId(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(accountService.findByCustomerId(customerId));
    }

    @GetMapping("/health")
    public String health() {
        return "Healthy";
    }
}
