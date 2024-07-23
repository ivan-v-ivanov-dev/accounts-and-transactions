package com.capgemini.transaction.service.contract;

import java.util.Map;

public interface TransactionService {
    Long create(long customerId, long accountId, double amount);

    Double findAccountBalance(long accountId);

    Map<Long, Double> findCustomerTransactions(long customerId);
}
