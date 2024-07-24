package com.capgemini.gateway.service;

import com.capgemini.gateway.model.CustomerResponse;
import com.capgemini.gateway.service.feign.TransactionFeignClient;
import com.capgemini.gateway.adapter.TransactionAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    @Mock
    private TransactionFeignClient transactionFeignClient;

    @Mock
    private TransactionAdapter transactionAdapter;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    void testCreate() {
        Long customerId = 1L;
        long accountIdResponse = 12345L;
        double initialCredit = 100.0;

        transactionService.create(customerId, accountIdResponse, initialCredit);

        verify(transactionFeignClient, times(1)).create(eq(customerId), eq(accountIdResponse), eq(initialCredit));
    }

    @Test
    void testFindAccountBalance() {
        long accountIdResponse = 12345L;
        double expectedBalance = 150.75;

        ResponseEntity<Double> responseEntity = ResponseEntity.ok(expectedBalance);
        when(transactionFeignClient.findAccountBalance(accountIdResponse)).thenReturn(responseEntity);

        double actualBalance = transactionService.findAccountBalance(accountIdResponse);

        assertEquals(expectedBalance, actualBalance, "The account balance should match the expected value.");
        verify(transactionFeignClient, times(1)).findAccountBalance(accountIdResponse);
    }

    @Test
    void testFindCustomerTransactions() {
        long customerId = 123L;
        CustomerResponse inputResponse = new CustomerResponse();
        CustomerResponse expectedResponse = new CustomerResponse();
        Map<Long, Double> transactionData = new HashMap<>();
        transactionData.put(1001L, 250.75);
        transactionData.put(1002L, 135.50);
        transactionData.put(1003L, 400.00);
        transactionData.put(1004L, 78.90);

        when(transactionFeignClient.findCustomerTransactions(customerId)).thenReturn(ResponseEntity.ok(transactionData));
        when(transactionAdapter.fromTransactionsDetailsToCustomerResponse(inputResponse, transactionData))
                .thenReturn(expectedResponse);

        CustomerResponse actualResponse = transactionService.findCustomerTransactions(inputResponse, customerId);

        verify(transactionFeignClient, times(1)).findCustomerTransactions(customerId);
        verify(transactionAdapter, times(1)).fromTransactionsDetailsToCustomerResponse(inputResponse, transactionData);

        assertEquals(expectedResponse, actualResponse);
    }
}
