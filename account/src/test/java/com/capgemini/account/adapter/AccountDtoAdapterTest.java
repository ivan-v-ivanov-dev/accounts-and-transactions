package com.capgemini.account.adapter;

import com.capgemini.account.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AccountDtoAdapterTest {

    private final AccountDtoAdapter adapter = new AccountDtoAdapter();

    @Test
    void testFromCustomerToCustomerString() {
        Customer customer = new Customer(1L, "John", "Doe");

        String result = adapter.fromCustomerToCustomerString(customer);

        assertEquals("Id:1/firstName:John/lastName:Doe/error:false", result);
    }

    @Test
    void testFromCustomerToCustomerStringWithError() {
        String result = adapter.fromCustomerToCustomerStringWithError();

        assertEquals("Id:id/firstName:string/lastName:string/error:true", result);
    }
}
