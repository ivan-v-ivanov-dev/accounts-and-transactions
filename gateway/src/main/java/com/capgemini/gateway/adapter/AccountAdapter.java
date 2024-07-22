package com.capgemini.gateway.adapter;

import com.capgemini.gateway.model.CustomerResponse;
import com.capgemini.models.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class AccountAdapter {

    public CustomerResponse fromCustomerDtoToCustomerResponse(CustomerDto dto) {
        return CustomerResponse.builder()
                .customerId(dto.getCustomerId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName()).build();
    }
}
