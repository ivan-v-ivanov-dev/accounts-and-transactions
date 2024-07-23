package com.capgemini.transaction.controller;

import com.capgemini.transaction.service.contract.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/customer/{customerId}/account/{accountId}/{amount}")
    public ResponseEntity<Long> create(@PathVariable("customerId") long customerId,
                                       @PathVariable("accountId") long accountId,
                                       @PathVariable("amount") double amount) {
        return ResponseEntity.ok(transactionService.create(customerId, accountId, amount));
    }

    @GetMapping("/account/{accountId}/balance")
    public ResponseEntity<Double> findAccountBalance(@PathVariable("accountId") long accountId) {
        return ResponseEntity.ok(transactionService.findAccountBalance(accountId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Map<Long, Double>> findCustomerTransactions(@PathVariable("customerId") long customerId) {
        return ResponseEntity.ok(transactionService.findCustomerTransactions(customerId));
    }

    @GetMapping("/health")
    public String health() {
        return "Healthy";
    }
}
