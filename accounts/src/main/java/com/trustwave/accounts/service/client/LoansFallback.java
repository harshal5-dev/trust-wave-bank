package com.trustwave.accounts.service.client;

import com.trustwave.accounts.dto.LoansDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallback implements LoansFeignClient {

  @Override
  public ResponseEntity<LoansDto> fetchLoan(String correlationId, String mobileNumber) {
    return null;
  }
}
