package com.capgemini.transaction.controller;

import com.capgemini.transaction.service.contract.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/account/{accountId}/{amount}")
    public ResponseEntity<Long> create(@PathVariable("accountId") long accountId,
                                       @PathVariable("amount") double amount) {
        return ResponseEntity.ok(transactionService.create(accountId, amount));
    }

    @GetMapping("/health")
    public String health() {
        return "Healthy";
    }
}
