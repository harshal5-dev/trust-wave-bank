package com.trustwave.accounts.service.client;

import com.trustwave.accounts.dto.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient {

  @Override
  public ResponseEntity<CardsDto> fetchCard(String correlationId, String mobileNumber) {
    return null;
  }
}
