package com.capgemini.gateway.adapter;

import com.capgemini.gateway.model.CustomerResponse;
import com.capgemini.gateway.model.TransactionResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TransactionAdapter {

    public CustomerResponse fromTransactionsDetailsToCustomerResponse(CustomerResponse response, Map<Long, Double> transactionData) {
        return response.toBuilder()
                .customerId(response.getCustomerId())
                .firstName(response.getFirstName())
                .lastName(response.getLastName())
                .transactions(transactionData.entrySet().stream()
                        .map(entry -> TransactionResponse.builder().accountId(entry.getKey()).balance(entry.getValue()).build())
                        .toList())
                .balance(transactionData.values().stream().mapToDouble(Double::doubleValue).sum()).build();
    }
}
