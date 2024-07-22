package com.capgemini.gateway.service;

import com.capgemini.gateway.model.AccountResponse;
import com.capgemini.gateway.model.CustomerResponse;
import com.capgemini.gateway.model.TransactionResponse;
import com.capgemini.gateway.service.contracts.AccountService;
import com.capgemini.gateway.service.feign.AccountFeignClient;
import com.capgemini.gateway.service.feign.TransactionFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountFeignClient accountFeignClient;
    private final TransactionFeignClient transactionFeignClient;

    @Override
    public AccountResponse create(Long customerID, double initialCredit) {
        ResponseEntity<Long> accountIdResponse = accountFeignClient.create(customerID);

        if (accountIdResponse.hasBody()) {
            transactionFeignClient.create(accountIdResponse.getBody(), initialCredit);
        }

        return AccountResponse.builder()
                .customerId(customerID)
                .balance(transactionFeignClient.findAccountBalance(accountIdResponse.getBody().longValue()).getBody()).build();
    }

    @Override
    public CustomerResponse customerDetails(Long customerId) {
        return CustomerResponse.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .balance(1.1)
                .transactions(Map.of(customerId, List.of(TransactionResponse.builder()
                        .accountId(1L).balance(111.1).date(LocalDateTime.now()).build()))).build();
    }
}
