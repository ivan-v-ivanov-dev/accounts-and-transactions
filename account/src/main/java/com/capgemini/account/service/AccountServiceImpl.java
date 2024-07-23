package com.capgemini.account.service;

import com.capgemini.account.adapter.AccountDtoAdapter;
import com.capgemini.account.exception.NoSuchCustomerException;
import com.capgemini.account.model.Account;
import com.capgemini.account.model.Customer;
import com.capgemini.account.repository.AccountRepository;
import com.capgemini.account.repository.CustomerRepository;
import com.capgemini.account.service.contract.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Slf4j
@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountDtoAdapter adapter;

    @Override
    public Long create(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElse(customerRepository.save(Customer.builder().id(customerId).build()));
        log.info(format("Customer %d retrieved/created", customer.getId()));

        Account account = accountRepository.save(new Account(customer));
        log.info(format("Account %d created", account.getId()));
        return account.getId();
    }

    @Override
    public String findByCustomerId(Long customerId) {
        try {
            return adapter.fromCustomerToCustomerString(customerRepository.findById(customerId)
                    .orElseThrow(() -> new NoSuchCustomerException(format("No such customer with Id %d", customerId))));
        } catch (NoSuchCustomerException ex) {
            log.error(ex.getMessage());
            return adapter.fromCustomerToCustomerStringWithError();
        }
    }
}
