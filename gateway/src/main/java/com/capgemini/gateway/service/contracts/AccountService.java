package com.capgemini.gateway.service.contracts;

import com.capgemini.gateway.model.AccountResponse;
import com.capgemini.gateway.model.CustomerResponse;

public interface AccountService {
    AccountResponse create(Long customerID, double initialCredit) throws IllegalAccessException;

    CustomerResponse customerDetails(Long customerId);
}
