package com.capgemini.gateway.adapter;

import com.capgemini.gateway.model.CustomerResponse;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionAdapterTest {

    private final TransactionAdapter transactionAdapter = new TransactionAdapter();

    @Test
    void testFromTransactionsDetailsToCustomerResponse() {
        CustomerResponse initialResponse = CustomerResponse.builder()
                .customerId(123L)
                .firstName("John")
                .lastName("Doe")
                .build();

        Map<Long, Double> transactionData = new HashMap<>();
        transactionData.put(1L, 100.0);
        transactionData.put(2L, 200.0);

        CustomerResponse updatedResponse = transactionAdapter.fromTransactionsDetailsToCustomerResponse(initialResponse, transactionData);

        assertEquals(123L, updatedResponse.getCustomerId());
        assertEquals("John", updatedResponse.getFirstName());
        assertEquals("Doe", updatedResponse.getLastName());
        assertEquals(2, updatedResponse.getTransactions().size());
        assertEquals(100.0, updatedResponse.getTransactions().get(0).getBalance());
        assertEquals(1L, updatedResponse.getTransactions().get(0).getAccountId());
        assertEquals(200.0, updatedResponse.getTransactions().get(1).getBalance());
        assertEquals(2L, updatedResponse.getTransactions().get(1).getAccountId());
        assertEquals(300.0, updatedResponse.getBalance());
    }

    @Test
    void testFromTransactionsDetailsToCustomerResponseWithEmptyTransactions() {
        CustomerResponse initialResponse = CustomerResponse.builder()
                .customerId(456L)
                .firstName("Jane")
                .lastName("Smith")
                .build();

        Map<Long, Double> transactionData = new HashMap<>();

        CustomerResponse updatedResponse = transactionAdapter.fromTransactionsDetailsToCustomerResponse(initialResponse, transactionData);

        assertEquals(456L, updatedResponse.getCustomerId());
        assertEquals("Jane", updatedResponse.getFirstName());
        assertEquals("Smith", updatedResponse.getLastName());
        assertEquals(0, updatedResponse.getTransactions().size());
        assertEquals(0.0, updatedResponse.getBalance());
    }

    @Test
    void testFromTransactionsDetailsToCustomerResponseWithMultipleTransactions() {
        CustomerResponse initialResponse = CustomerResponse.builder()
                .customerId(789L)
                .firstName("Alice")
                .lastName("Wonderland")
                .build();

        Map<Long, Double> transactionData = new HashMap<>();
        transactionData.put(1L, 500.0);
        transactionData.put(2L, 150.0);
        transactionData.put(3L, 250.0);

        CustomerResponse updatedResponse = transactionAdapter.fromTransactionsDetailsToCustomerResponse(initialResponse, transactionData);

        assertEquals(789L, updatedResponse.getCustomerId());
        assertEquals("Alice", updatedResponse.getFirstName());
        assertEquals("Wonderland", updatedResponse.getLastName());
        assertEquals(3, updatedResponse.getTransactions().size());
        assertEquals(500.0, updatedResponse.getTransactions().get(0).getBalance());
        assertEquals(1L, updatedResponse.getTransactions().get(0).getAccountId());
        assertEquals(150.0, updatedResponse.getTransactions().get(1).getBalance());
        assertEquals(2L, updatedResponse.getTransactions().get(1).getAccountId());
        assertEquals(250.0, updatedResponse.getTransactions().get(2).getBalance());
        assertEquals(3L, updatedResponse.getTransactions().get(2).getAccountId());
        assertEquals(900.0, updatedResponse.getBalance());
    }
}
