package com.capgemini.account.service.contract;

import com.capgemini.account.exception.NoSuchCustomerException;

public interface AccountService {
    Long create(Long customerId);

    String findByCustomerId(Long customerId) throws NoSuchCustomerException;
}
