package com.capgemini.account.service;

import com.capgemini.account.adapter.AccountDtoAdapter;
import com.capgemini.account.model.Account;
import com.capgemini.account.model.Customer;
import com.capgemini.account.repository.AccountRepository;
import com.capgemini.account.repository.CustomerRepository;
import com.capgemini.account.service.contract.AccountService;
import com.capgemini.models.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@AllArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountDtoAdapter adapter;

    @Override
    public Long create(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElse(customerRepository.save(Customer.builder().id(customerId).build()));
        log.info(format("Customer %d retrieved/created", customer.getId()));

        Account account = accountRepository.save(Account.builder().customer(customer).build());
        log.info(format("Account %d created", account.getId()));
        return account.getId();
    }

    @Override
    public CustomerDto findByCustomerId(Long customerId) {
        return adapter.fromCustomerToCustomerDto(customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("No such customer")));
    }
}
