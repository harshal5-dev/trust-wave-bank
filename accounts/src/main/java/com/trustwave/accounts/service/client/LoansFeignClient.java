package com.trustwave.accounts.service.client;

import com.trustwave.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoansFeignClient {

  @GetMapping(value = "/loans/fetch", consumes = "application/json")
  ResponseEntity<LoansDto> fetchLoan(@RequestParam String mobileNumber);
}
