package com.capgemini.gateway.adapter;

import com.capgemini.gateway.model.CustomerResponse;
import com.capgemini.gateway.model.TransactionResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TransactionAdapter {

    public void fromTransactionsDetailsToCustomerResponse(CustomerResponse response, Map<Long, Double> transactionData) {
        response.toBuilder()
                .transactions(transactionData.entrySet().stream()
                        .map(entry -> TransactionResponse.builder().accountId(entry.getKey()).balance(entry.getValue()).build())
                        .toList())
                .balance(transactionData.values().stream().mapToDouble(Double::doubleValue).sum()).build();
    }
}
