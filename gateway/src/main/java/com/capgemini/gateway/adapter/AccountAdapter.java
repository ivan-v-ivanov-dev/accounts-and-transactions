package com.capgemini.gateway.adapter;

import com.capgemini.gateway.model.CustomerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountAdapter {

    public CustomerResponse fromCustomerDataToCustomerResponse(String customerData) {
        long id = 0L;
        String firstName = "";
        String lastName = "";
        String[] parts = customerData.split("/");

        try {
            id = Long.parseLong(parts[0].split(":")[1]);
            firstName = parts[1].split(":")[1];
            lastName = parts[2].split(":")[1];
        } catch (IndexOutOfBoundsException ex) {
            log.warn(ex.getMessage() + " | " + customerData);
        }

        return CustomerResponse.builder()
                .customerId(id)
                .firstName(firstName)
                .lastName(lastName).build();
    }
}
