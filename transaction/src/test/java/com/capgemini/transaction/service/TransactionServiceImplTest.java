package com.capgemini.transaction.service;

import com.capgemini.transaction.adapter.TransactionDataAdapter;
import com.capgemini.transaction.model.Transaction;
import com.capgemini.transaction.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionDataAdapter adapter;

    @InjectMocks
    private TransactionServiceImpl transactionService;


    @Test
    void testFindCustomerTransactions() {
        long customerId = 1L;
        List<Transaction> mockTransactions = List.of(
                new Transaction(1L, 1L, 100.0, LocalDateTime.now()),
                new Transaction(2L, 1L, 200.0, LocalDateTime.now())
        );

        Map<Long, Double> mockTransactionData = Map.of(
                1L, 100.0,
                2L, 200.0
        );

        when(transactionRepository.findByCustomerId(anyLong())).thenReturn(mockTransactions);
        when(adapter.fromListTransactionsToTransactionData(mockTransactions)).thenReturn(mockTransactionData);

        Map<Long, Double> result = transactionService.findCustomerTransactions(customerId);

        assertEquals(mockTransactionData, result);
    }

    @Test
    void testCreateTransaction() {
        long customerId = 1L;
        long accountId = 2L;
        double amount = 100.0;

        Transaction transaction = new Transaction(customerId, accountId, amount, LocalDateTime.now());
        transaction.setId(1L);

        when(transactionRepository.save(argThat(t ->
                t.getCustomerId() == customerId &&
                        t.getAccountId() == accountId &&
                        t.getAmount() == amount))).thenReturn(transaction);

        Long transactionId = transactionService.create(customerId, accountId, amount);

        verify(transactionRepository, times(1)).save(argThat(t ->
                t.getCustomerId() == customerId &&
                        t.getAccountId() == accountId &&
                        t.getAmount() == amount &&
                        t.getTimestamp() != null));

        assertEquals(1L, transactionId);
    }

    @Test
    void testFindAccountBalance() {
        long accountId = 1L;
        when(transactionRepository.findAccountBalance(anyLong())).thenReturn(500.0);
        Double result = transactionService.findAccountBalance(accountId);

        assertEquals(500.0, result);
    }
}
