package com.capgemini.transaction.service;

import com.capgemini.models.dto.TransactionDto;
import com.capgemini.transaction.adapter.TransactionDtoAdapter;
import com.capgemini.transaction.model.Transaction;
import com.capgemini.transaction.repository.TransactionRepository;
import com.capgemini.transaction.service.contract.TransactionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private final TransactionRepository transactionRepository;
    private final TransactionDtoAdapter adapter;

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

    @Override
    public TransactionDto findCustomerTransactions(long customerId) {
        return adapter.fromListTransactionsToTransactionDto(transactionRepository.findByCustomerId(customerId));
    }
}
