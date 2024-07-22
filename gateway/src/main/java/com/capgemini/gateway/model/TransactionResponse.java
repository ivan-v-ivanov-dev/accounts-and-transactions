package com.capgemini.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class TransactionResponse {

    private String accountId;
    private double balance;
    private LocalDateTime date;
}
