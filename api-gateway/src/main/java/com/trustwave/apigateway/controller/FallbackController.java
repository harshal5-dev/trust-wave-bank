package com.trustwave.apigateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

  @RequestMapping("/contactSupport")
  public Mono<String> contactSupport() {
    return Mono.just("An error occurred. Please try again later or contact the support team!!!");
  }
}
