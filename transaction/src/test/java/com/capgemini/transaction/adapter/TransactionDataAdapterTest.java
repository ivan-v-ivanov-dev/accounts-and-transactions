package com.capgemini.transaction.adapter;

import com.capgemini.transaction.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionDataAdapterTest {

    private TransactionDataAdapter transactionAdapter;

    @BeforeEach
    void setUp() {
        transactionAdapter = new TransactionDataAdapter();
    }

    @Test
    void testFromListTransactionsToTransactionData() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(1L, 1L, 100.0, null),
                new Transaction(1L, 1L, 200.0, null),
                new Transaction(2L, 1L, -50.0, null),
                new Transaction(2L, 2L, 150.0, null),
                new Transaction(3L, 2L, 50.0, null)
        );

        Map<Long, Double> result = transactionAdapter.fromListTransactionsToTransactionData(transactions);

        assertEquals(2, result.size());
        assertEquals(250.0, result.get(1L));
        assertEquals(200.0, result.get(2L));
    }
}
