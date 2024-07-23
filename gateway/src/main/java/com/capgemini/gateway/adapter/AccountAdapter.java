package com.capgemini.gateway.adapter;

import com.capgemini.gateway.model.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class AccountAdapter {

    public CustomerResponse fromCustomerDataToCustomerResponse(String customerData) {
        String[] parts = customerData.split("/");

        long id = Long.parseLong(parts[0].split(":")[1]);
        String firstName = parts[1].split(":")[1];
        String lastName = parts[2].split(":")[1];

        return CustomerResponse.builder()
                .customerId(id)
                .firstName(firstName)
                .lastName(lastName).build();
    }
}
