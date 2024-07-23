package com.capgemini.gateway.controller;

import com.capgemini.gateway.exception.GeneralAccountException;
import com.capgemini.gateway.model.AccountResponse;
import com.capgemini.gateway.model.CustomerResponse;
import com.capgemini.gateway.service.contracts.AccountService;
import com.capgemini.gateway.service.contracts.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class GatewayController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    @PostMapping("/account")
    public ResponseEntity<AccountResponse> create(@RequestParam Long customerID,
                                                  @RequestParam double initialCredit) throws IllegalAccessException {
        return ResponseEntity.ok(accountService.create(customerID, initialCredit));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<CustomerResponse> customerDetails(@PathVariable Long customerId) throws IllegalAccessException, GeneralAccountException {
        return ResponseEntity.ok(accountService.customerDetails(customerId));
    }

    @GetMapping("/health")
    public String health() {
        return "Healthy";
    }
}
