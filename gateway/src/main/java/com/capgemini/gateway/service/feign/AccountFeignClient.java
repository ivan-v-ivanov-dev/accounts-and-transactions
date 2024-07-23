package com.capgemini.gateway.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "${account.service.feign.client.name}", url = "${account.service.url}")
public interface AccountFeignClient {

    @PostMapping("${account.create}")
    ResponseEntity<Long> create(@PathVariable("customerId") Long customerId);

    @GetMapping("/customer/{customerId}")
    ResponseEntity<String> findByCustomerId(@PathVariable("customerId") Long customerId);
}
