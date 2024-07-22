package com.capgemini.gateway.service.contracts;

import com.capgemini.gateway.model.AccountResponse;

public interface AccountService {
    AccountResponse create(String customerID, double initialCredit);
}
