package com.capgemini.transaction.service.contract;

import com.capgemini.models.dto.TransactionDto;

public interface TransactionService {
    Long create(long customerId, long accountId, double amount);

    Double findAccountBalance(long accountId);

    TransactionDto findCustomerTransactions(long customerId);
}
