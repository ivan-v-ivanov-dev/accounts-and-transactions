package com.capgemini.account.service.contract;

import com.capgemini.models.dto.CustomerDto;

public interface AccountService {
    Long create(Long customerId);

    CustomerDto findByCustomerId(Long customerId);
}
