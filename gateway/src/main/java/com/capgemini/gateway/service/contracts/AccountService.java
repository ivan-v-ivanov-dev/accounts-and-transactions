package com.capgemini.gateway.service.contracts;

import com.capgemini.gateway.model.AccountResponse;
import com.capgemini.gateway.model.CustomerResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {
    AccountResponse create(Long customerID, double initialCredit) throws IllegalAccessException;

    CustomerResponse customerDetails(Long customerId) throws IllegalAccessException;
}
