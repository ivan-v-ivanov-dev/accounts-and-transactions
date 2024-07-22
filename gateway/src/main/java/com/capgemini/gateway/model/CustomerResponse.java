package com.capgemini.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CustomerResponse {

    private String firstName;
    private String lastName;
    private double balance;
    Map<String, List<TransactionResponse>> transactions;

}
