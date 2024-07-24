package com.capgemini.gateway.service;

import com.capgemini.gateway.model.AccountResponse;
import com.capgemini.gateway.service.contracts.TransactionService;
import com.capgemini.gateway.service.feign.AccountFeignClient;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @Mock
    private TransactionService transactionService;

    @Mock
    private AccountFeignClient accountFeignClient;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void testCreateAccountWithInitialCredit() throws IllegalAccessException {
        Long customerID = 123L;
        double initialCredit = 100.0;
        long accountIdResponse = 456L;
        double accountBalance = 150.0;

        when(accountFeignClient.create(customerID)).thenReturn(ResponseEntity.ok(accountIdResponse));
        doNothing().when(transactionService).create(customerID, accountIdResponse, initialCredit);
        when(transactionService.findAccountBalance(accountIdResponse)).thenReturn(accountBalance);

        AccountResponse expectedResponse = AccountResponse.builder()
                .customerId(customerID)
                .balance(accountBalance).build();

        AccountResponse actualResponse = accountService.create(customerID, initialCredit);

        verify(accountFeignClient).create(customerID);
        verify(transactionService).create(customerID, accountIdResponse, initialCredit);
        verify(transactionService).findAccountBalance(accountIdResponse);

        assertEquals(expectedResponse.getCustomerId(), actualResponse.getCustomerId());
        assertEquals(expectedResponse.getBalance(), actualResponse.getBalance());
    }

    @Test
    void testCreateAccountWithoutInitialCredit() throws IllegalAccessException {
        Long customerID = 123L;
        double initialCredit = 0.0;
        long accountIdResponse = 456L;
        double accountBalance = 150.0;

        when(accountFeignClient.create(customerID)).thenReturn(ResponseEntity.ok(accountIdResponse));
        when(transactionService.findAccountBalance(accountIdResponse)).thenReturn(accountBalance);

        AccountResponse expectedResponse = AccountResponse.builder()
                .customerId(customerID)
                .balance(accountBalance).build();

        AccountResponse actualResponse = accountService.create(customerID, initialCredit);

        verify(accountFeignClient).create(customerID);
        verify(transactionService, never()).create(anyLong(), anyLong(), anyDouble());
        verify(transactionService).findAccountBalance(accountIdResponse);

        assertEquals(expectedResponse.getCustomerId(), actualResponse.getCustomerId());
    }

    @Test
    void testCreateAccountWithFeignException() {
        Long customerID = 123L;
        double initialCredit = 100.0;

        when(accountFeignClient.create(customerID)).thenThrow(FeignException.class);

        IllegalAccessException thrown = assertThrows(IllegalAccessException.class, () ->
                accountService.create(customerID, initialCredit));

        assertEquals("Transaction malfunction. Please, call support", thrown.getMessage());
    }
}
