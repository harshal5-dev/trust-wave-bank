package com.trustwave.accounts.service.client;

import com.trustwave.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {

  @GetMapping(value = "/cards/fetch", consumes = "application/json")
  ResponseEntity<CardsDto> fetchCard(@RequestParam String mobileNumber);

}
