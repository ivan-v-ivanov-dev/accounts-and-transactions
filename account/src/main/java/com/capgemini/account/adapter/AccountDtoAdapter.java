package com.capgemini.account.adapter;

import com.capgemini.account.model.Customer;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class AccountDtoAdapter {

    public String fromCustomerToCustomerString(Customer customer) {
        return format("Id:%d/firstName:%s/lastName:%s/error:false",
                customer.getId(), customer.getFirstName(), customer.getLastName());
    }

    public String fromCustomerToCustomerStringWithError() {
        return "Id:id/firstName:string/lastName:string/error:true";
    }
}
