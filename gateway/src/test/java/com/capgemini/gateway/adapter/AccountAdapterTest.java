package com.capgemini.gateway.adapter;

import com.capgemini.gateway.model.CustomerResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountAdapterTest {

    private final AccountAdapter accountAdapter = new AccountAdapter();

    @Test
    void testFromCustomerDataToCustomerResponse() {
        String customerData = "Id:123/firstName:John/lastName:Doe";

        CustomerResponse response = accountAdapter.fromCustomerDataToCustomerResponse(customerData);

        assertEquals(123L, response.getCustomerId());
        assertEquals("John", response.getFirstName());
        assertEquals("Doe", response.getLastName());
    }

    @Test
    void testFromCustomerDataToCustomerResponseWithDifferentData() {
        String customerData = "Id:456/firstName:Jane/lastName:Smith";

        CustomerResponse response = accountAdapter.fromCustomerDataToCustomerResponse(customerData);

        assertEquals(456L, response.getCustomerId());
        assertEquals("Jane", response.getFirstName());
        assertEquals("Smith", response.getLastName());
    }

    @Test
    void testFromCustomerDataToCustomerResponseWithEmptyStrings() {
        String customerData = "Id:0/firstName:/lastName:";

        CustomerResponse response = accountAdapter.fromCustomerDataToCustomerResponse(customerData);

        assertEquals(0L, response.getCustomerId());
        assertEquals("", response.getFirstName());
        assertEquals("", response.getLastName());
    }
}
