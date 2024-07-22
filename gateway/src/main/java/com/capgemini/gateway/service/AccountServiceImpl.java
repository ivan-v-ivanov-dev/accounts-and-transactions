package com.capgemini.gateway.service;

import com.capgemini.gateway.model.AccountResponse;
import com.capgemini.gateway.service.contracts.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public AccountResponse create(String customerID, double initialCredit) {
        return AccountResponse.builder().accountId("1").customerId(customerID).balance(initialCredit).build();
    }
}
