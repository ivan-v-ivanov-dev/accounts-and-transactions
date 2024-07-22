package com.capgemini.gateway.service;

import com.capgemini.gateway.model.AccountResponse;
import com.capgemini.gateway.model.CustomerResponse;
import com.capgemini.gateway.model.TransactionResponse;
import com.capgemini.gateway.service.contracts.AccountService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public AccountResponse create(String customerID, double initialCredit) {
        return AccountResponse.builder().accountId("1").customerId(customerID).balance(initialCredit).build();
    }

    @Override
    public CustomerResponse customerDetails(String customerId) {
        return CustomerResponse.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .balance(1.1)
                .transactions(Map.of(customerId, List.of(TransactionResponse.builder()
                        .accountId("Account1").balance(111.1).date(LocalDateTime.now()).build()))).build();
    }
}
