package com.capgemini.transaction.service;

import com.capgemini.transaction.adapter.TransactionDataAdapter;
import com.capgemini.transaction.model.Transaction;
import com.capgemini.transaction.repository.TransactionRepository;
import com.capgemini.transaction.service.contract.TransactionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

import static java.lang.String.format;

@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private final TransactionRepository transactionRepository;
    private final TransactionDataAdapter adapter;

    @Override
    public Map<Long, Double> findCustomerTransactions(long customerId) {
        Map<Long, Double> customerTransactions = adapter.fromListTransactionsToTransactionData(
                transactionRepository.findByCustomerId(customerId));
        log.info(format("Retrieve transactions for customer %d", customerId));
        return customerTransactions;
    }

    @Override
    public Long create(long customerId, long accountId, double amount) {
        Transaction transaction = transactionRepository.save(Transaction.builder()
                .customerId(customerId)
                .accountId(accountId)
                .amount(amount)
                .timestamp(LocalDateTime.now()).build());
        log.info(format("Transaction %d for customer %d with account %d and amount %.2f created",
                transaction.getId(), customerId, transaction.getAccountId(), transaction.getAmount()));

        return transaction.getId();
    }

    @Override
    public Double findAccountBalance(long accountId) {
        return transactionRepository.findAccountBalance(accountId);
    }
}
