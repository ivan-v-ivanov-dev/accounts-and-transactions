package com.capgemini.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder(toBuilder = true)
public class CustomerResponse {

    private long customerId;
    private String firstName;
    private String lastName;
    private double balance;
    List<TransactionResponse> transactions;

}
