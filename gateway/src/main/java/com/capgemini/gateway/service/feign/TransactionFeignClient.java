package com.capgemini.gateway.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "${transaction.service.feign.client.name}", url = "${transaction.service.url}")
public interface TransactionFeignClient {

    @PostMapping("${transaction.create}")
    ResponseEntity<Long> create(@PathVariable("accountId") long accountId, @PathVariable("amount") double amount);

    @GetMapping("${transaction.find.account.balance}")
    ResponseEntity<Double> findAccountBalance(@PathVariable("accountId") long accountId);
}
