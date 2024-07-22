package com.capgemini.transaction.service.contract;

public interface TransactionService {
    Long create(long accountId, double amount);
}
