package com.capgemini.gateway.controller;

import com.capgemini.gateway.exception.GeneralAccountException;
import com.capgemini.gateway.model.AccountResponse;
import com.capgemini.gateway.service.contracts.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class GatewayController {

    private final AccountService accountService;

    @PostMapping("/account")
    public ResponseEntity<AccountResponse> create(@RequestParam Long customerID,
                                                  @RequestParam double initialCredit) throws IllegalAccessException {
        return ResponseEntity.ok(accountService.create(customerID, initialCredit));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> customerDetails(@PathVariable Long customerId) throws IllegalAccessException, GeneralAccountException {
        try {
            return ResponseEntity.ok(accountService.customerDetails(customerId));
        } catch (GeneralAccountException | IllegalAccessException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/health")
    public String health() {
        return "Healthy";
    }
}
