package com.capgemini.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class TransactionDto {

    private long customerId;
    private Map<Long, Double> accountsAmounts;

}
