package com.capgemini.account.service;

import com.capgemini.account.adapter.AccountDtoAdapter;
import com.capgemini.account.model.Account;
import com.capgemini.account.model.Customer;
import com.capgemini.account.repository.AccountRepository;
import com.capgemini.account.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountDtoAdapter adapter;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void testCreateCustomerAndAccount() {
        Long customerId = 1L;

        Customer existingCustomer = Customer.builder().id(customerId).build();
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));

        Account newAccount = new Account(existingCustomer);
        newAccount.setId(1L);
        when(accountRepository.save(argThat(account ->
                account.getCustomer().getId() == existingCustomer.getId()))).thenReturn(newAccount);

        Long accountId = accountService.create(customerId);

        verify(customerRepository, times(1)).findById(eq(customerId));

        verify(customerRepository, times(1)).save(argThat(c ->
                c.getId() == customerId && c.getClass().equals(Customer.class)));

        verify(accountRepository, times(1)).save(argThat(a ->
                a.getCustomer().equals(existingCustomer)));

        assertEquals(1L, accountId);
    }

    @Test
    void testFindByCustomerId_CustomerExists() {
        Long customerId = 1L;
        Customer customer = new Customer(customerId, "John","Doe");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(adapter.fromCustomerToCustomerString(customer)).thenReturn("Customer: John Doe");

        String result = accountService.findByCustomerId(customerId);

        assertEquals("Customer: John Doe", result);
        verify(customerRepository, times(1)).findById(customerId);
        verify(adapter, times(1)).fromCustomerToCustomerString(customer);
    }

    @Test
    void testFindByCustomerId_RepositoryThrowsException() {
        Long customerId = 1L;

        when(customerRepository.findById(customerId)).thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class,() -> accountService.findByCustomerId(customerId));
    }
}
