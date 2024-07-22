package com.capgemini.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CustomerDto {

    private long customerId;

    private String firstName;

    private String lastName;
}
