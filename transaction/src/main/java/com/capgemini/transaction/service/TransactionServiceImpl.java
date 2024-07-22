package com.capgemini.transaction.service;

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

    @Override
    public Long create(long accountId, double amount) {
        Transaction transaction = transactionRepository.save(Transaction.builder()
                .accountId(accountId)
                .amount(amount)
                .timestamp(LocalDateTime.now()).build());
        log.info(format("Transaction %d for account %d with amount %.2f created",
                transaction.getId(), transaction.getAccountId(), transaction.getAmount()));

        return transaction.getId();
    }

    @Override
    public Double findAccountBalance(long accountId) {
        return transactionRepository.findAccountBalance(accountId);
    }
}
