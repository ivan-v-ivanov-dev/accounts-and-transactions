package com.capgemini.gateway.adapter;

import com.capgemini.gateway.model.CustomerResponse;
import com.capgemini.gateway.model.TransactionResponse;
import com.capgemini.models.dto.TransactionDto;
import org.springframework.stereotype.Component;

@Component
public class TransactionAdapter {

    public void fromTransactionsDetailsToCustomerResponse(CustomerResponse response, TransactionDto dto) {
        response.toBuilder()
                .transactions(dto.getAccountsAmounts().entrySet().stream()
                        .map(entry -> TransactionResponse.builder().accountId(entry.getKey()).balance(entry.getValue()).build())
                        .toList())
                .balance(dto.getAccountsAmounts().values().stream().mapToDouble(Double::doubleValue).sum()).build();
    }
}
