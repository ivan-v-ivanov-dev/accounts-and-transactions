package com.capgemini.gateway.service;

import com.capgemini.gateway.adapter.AccountAdapter;
import com.capgemini.gateway.adapter.TransactionAdapter;
import com.capgemini.gateway.model.AccountResponse;
import com.capgemini.gateway.model.CustomerResponse;
import com.capgemini.gateway.service.contracts.AccountService;
import com.capgemini.gateway.service.feign.AccountFeignClient;
import com.capgemini.gateway.service.feign.TransactionFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountFeignClient accountFeignClient;
    private final TransactionFeignClient transactionFeignClient;
    private final AccountAdapter accountAdapter;
    private final TransactionAdapter transactionAdapter;

    @Override
    public AccountResponse create(Long customerID, double initialCredit) {
        ResponseEntity<Long> accountIdResponse = accountFeignClient.create(customerID);

        if (accountIdResponse.hasBody()) {
            transactionFeignClient.create(customerID, accountIdResponse.getBody(), initialCredit);
        }

        return AccountResponse.builder()
                .customerId(customerID)
                .balance(transactionFeignClient.findAccountBalance(accountIdResponse.getBody().longValue()).getBody()).build();
    }

    @Override
    public CustomerResponse customerDetails(Long customerId) {
        CustomerResponse response = accountAdapter.fromCustomerDtoToCustomerResponse(
                accountFeignClient.findByCustomerId(customerId).getBody());
        transactionAdapter.fromTransactionsDetailsToCustomerResponse(response,
                transactionFeignClient.findCustomerTransactions(customerId).getBody());
        return response;
    }
}
