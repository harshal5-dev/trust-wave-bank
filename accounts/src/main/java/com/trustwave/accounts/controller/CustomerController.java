package com.trustwave.accounts.controller;

import com.trustwave.accounts.dto.CustomerDetailsDto;
import com.trustwave.accounts.dto.ErrorResponseDto;
import com.trustwave.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "REST APIs for Customers in TrustWave",
        description = "REST APIS in TrustWave to FETCH customer details."
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {

  private final ICustomerService iCustomerService;
  private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

  public CustomerController(ICustomerService iCustomerService) {
    this.iCustomerService = iCustomerService;
  }


  @Operation(
          summary = "Fetch Customer Details REST API",
          description = "REST API to fetch Customer &  Customer details based on a mobile number"
  )
  @ApiResponses({
          @ApiResponse(
                  responseCode = "200",
                  description = "HTTP Status OK"
          ),
          @ApiResponse(
                  responseCode = "500",
                  description = "HTTP Status Internal Server Error",
                  content = @Content(
                          schema = @Schema(implementation = ErrorResponseDto.class)
                  )
          )
  }
  )
  @GetMapping("/fetchCustomerDetails")
  public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("trustwave-correlation-id") String correlationId,
                                                                 @RequestParam
                                                                 @Pattern(regexp = "^\\d{10}$", message = "Mobile number should be 10 digits") String mobileNumber) {
    logger.debug("fetchCustomerDetails method start");
    CustomerDetailsDto customerDetails = iCustomerService.getCustomerDetails(mobileNumber, correlationId);
    logger.debug("fetchCustomerDetails method end");
    return ResponseEntity.ok(customerDetails);
  }
}
