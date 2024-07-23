package com.capgemini.transaction.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "account_id")
    private long accountId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public Transaction(long customerId, long accountId, double amount, LocalDateTime timestamp) {
        this.customerId = customerId;
        this.accountId = accountId;
        this.amount = amount;
        this.timestamp = timestamp;
    }
}
