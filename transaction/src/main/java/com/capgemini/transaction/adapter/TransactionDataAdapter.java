package com.capgemini.transaction.adapter;

import com.capgemini.transaction.model.Transaction;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@NoArgsConstructor
public class TransactionDataAdapter {

    public Map<Long, Double> fromListTransactionsToTransactionData(List<Transaction> transactions) {
        Map<Long, Double> transactionData = new LinkedHashMap<>();

        transactions.forEach(transaction -> {
            double oldValue = 0;
            long currentTransactionAccountId = transaction.getAccountId();

            if (transactionData.containsKey(currentTransactionAccountId)) {
                oldValue = transactionData.get(currentTransactionAccountId);
            }

            transactionData.put(currentTransactionAccountId, oldValue + transaction.getAmount());
        });

        return transactionData;
    }
}
