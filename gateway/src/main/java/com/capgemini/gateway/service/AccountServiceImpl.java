package com.capgemini.gateway.service;

import com.capgemini.gateway.adapter.AccountAdapter;
import com.capgemini.gateway.adapter.TransactionAdapter;
import com.capgemini.gateway.exception.GeneralAccountException;
import com.capgemini.gateway.model.AccountResponse;
import com.capgemini.gateway.model.CustomerResponse;
import com.capgemini.gateway.service.contracts.AccountService;
import com.capgemini.gateway.service.feign.AccountFeignClient;
import com.capgemini.gateway.service.feign.TransactionFeignClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@AllArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountFeignClient accountFeignClient;
    private final TransactionFeignClient transactionFeignClient;
    private final AccountAdapter accountAdapter;
    private final TransactionAdapter transactionAdapter;

    @Override
    public AccountResponse create(Long customerID, double initialCredit) throws IllegalAccessException {
        try {
            long accountIdResponse = accountFeignClient.create(customerID).getBody();
            log.info("Account %d created for customer %d", accountIdResponse, customerID);

            if (initialCredit != 0.0) {
                transactionFeignClient.create(customerID, accountIdResponse, initialCredit);
                log.info("Transaction created for account %d", accountIdResponse);
            }

            return AccountResponse.builder()
                    .customerId(customerID)
                    .balance(transactionFeignClient.findAccountBalance(accountIdResponse).getBody()).build();
        } catch (FeignException ex) {
            log.error(ex.getMessage());
            throw new IllegalAccessException("Transaction malfunction. Please, call support");
        }
    }

    @Override
    public CustomerResponse customerDetails(Long customerId) throws IllegalAccessException, GeneralAccountException {
        try {
            String customerData = accountFeignClient.findByCustomerId(customerId).getBody();
            if (hasErrorInCustomerData(customerData)) {
                throw new GeneralAccountException(format("Wrong customer Id &d", customerId));
            }

            CustomerResponse response = accountAdapter.fromCustomerDataToCustomerResponse(customerData);
            transactionAdapter.fromTransactionsDetailsToCustomerResponse(response,
                    transactionFeignClient.findCustomerTransactions(customerId).getBody());
            log.info(format("Retrieve transaction information for customer &d", customerId));

            return response;
        } catch (FeignException ex) {
            log.error(ex.getMessage());
            throw new IllegalAccessException("Customer information unavailable. Please, call support");
        } catch (GeneralAccountException ex) {
            throw ex;
        }
    }

    private boolean hasErrorInCustomerData(String customerData) {
        return customerData.endsWith("true");
    }
}
