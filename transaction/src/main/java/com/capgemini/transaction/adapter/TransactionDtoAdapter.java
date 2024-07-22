package com.capgemini.transaction.adapter;

import com.capgemini.models.dto.TransactionDto;
import com.capgemini.transaction.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class TransactionDtoAdapter {

    public TransactionDto fromListTransactionsToTransactionDto(List<Transaction> transactions) {
        TransactionDto dto = TransactionDto.builder()
                .customerId(transactions.get(0).getCustomerId())
                .accountsAmounts(new HashMap<>()).build();

        transactions.forEach(transaction -> {
            double oldValue = 0;

            if (dto.getAccountsAmounts().containsKey(transaction.getAccountId())) {
                oldValue = dto.getAccountsAmounts().get(transaction.getAccountId());
            }

            dto.getAccountsAmounts().put(transaction.getAccountId(), oldValue + transaction.getAmount());
        });

        return dto;
    }
}
