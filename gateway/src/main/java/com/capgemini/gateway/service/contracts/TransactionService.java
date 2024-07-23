package com.capgemini.gateway.service.contracts;

import com.capgemini.gateway.model.CustomerResponse;

public interface TransactionService {
    void create(Long customerID, long accountIdResponse, double initialCredit);

    double findAccountBalance(long accountIdResponse);

    CustomerResponse findCustomerTransactions(CustomerResponse response, long customerId);
}
