package com.capgemini.transaction.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "account_id")
    private long accountId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
