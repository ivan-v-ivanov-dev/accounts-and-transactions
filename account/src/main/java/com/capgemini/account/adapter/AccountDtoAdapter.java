package com.capgemini.account.adapter;

import com.capgemini.account.model.Customer;
import com.capgemini.models.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoAdapter {

    public CustomerDto fromCustomerToCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .customerId(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName()).build();
    }
}
