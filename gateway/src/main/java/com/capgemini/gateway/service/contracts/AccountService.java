package com.capgemini.gateway.service.contracts;

import com.capgemini.gateway.model.AccountResponse;
import com.capgemini.gateway.model.CustomerResponse;

public interface AccountService {
    AccountResponse create(String customerID, double initialCredit);

    CustomerResponse customerDetails(String customerId);
}
