package com.capgemini.gateway.service;

import com.capgemini.gateway.adapter.TransactionAdapter;
import com.capgemini.gateway.model.CustomerResponse;
import com.capgemini.gateway.service.contracts.TransactionService;
import com.capgemini.gateway.service.feign.TransactionFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionFeignClient transactionFeignClient;
    private final TransactionAdapter transactionAdapter;

    @Override
    public void create(Long customerID, long accountIdResponse, double initialCredit) {
        transactionFeignClient.create(customerID, accountIdResponse, initialCredit);
    }

    @Override
    public double findAccountBalance(long accountIdResponse) {
        return transactionFeignClient.findAccountBalance(accountIdResponse).getBody();
    }

    @Override
    public CustomerResponse findCustomerTransactions(CustomerResponse response, long customerId) {
        return transactionAdapter.fromTransactionsDetailsToCustomerResponse(response,
                transactionFeignClient.findCustomerTransactions(customerId).getBody());
    }
}
