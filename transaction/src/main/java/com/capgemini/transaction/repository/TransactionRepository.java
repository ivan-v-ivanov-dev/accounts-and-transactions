package com.capgemini.transaction.repository;

import com.capgemini.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.accountId = :accountId")
    double findAccountBalance(@Param("accountId") long accountId);

    List<Transaction> findByCustomerId(long customerId);
}
