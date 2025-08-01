package com.trustwave.cards.controller;

import com.trustwave.cards.constants.CardsConstants;
import com.trustwave.cards.dto.CardsContactInfoDto;
import com.trustwave.cards.dto.CardsDto;
import com.trustwave.cards.dto.ErrorResponseDto;
import com.trustwave.cards.dto.ResponseDto;
import com.trustwave.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Trust Wave
 */

@Tag(
        name = "CRUD REST APIs for Cards in TrustWave",
        description = "CRUD REST APIs in TrustWave to CREATE, UPDATE, FETCH AND DELETE card details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CardsController {

  @Value("${build.version}")
  private String buildVersion;

  private final ICardsService iCardsService;
  private final Environment environment;
  private final CardsContactInfoDto cardsContactInfoDto;

  private static final Logger logger = LoggerFactory.getLogger(CardsController.class);

  public CardsController(ICardsService iCardsService,
                         Environment environment,
                         CardsContactInfoDto cardsContactInfoDto)  {
    this.iCardsService = iCardsService;
    this.environment = environment;
    this.cardsContactInfoDto = cardsContactInfoDto;
  }

  @Operation(
          summary = "Create Card REST API",
          description = "REST API to create new Card inside EazyBank"
  )
  @ApiResponses({
          @ApiResponse(
                  responseCode = "201",
                  description = "HTTP Status CREATED"
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
  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createCard(@RequestParam
                                                  @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                String mobileNumber) {
    iCardsService.createCard(mobileNumber);
    ResponseDto responseDto = new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201);
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(responseDto);
  }

  @Operation(
          summary = "Fetch Card Details REST API",
          description = "REST API to fetch card details based on a mobile number"
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
  })
  @GetMapping("/fetch")
  public ResponseEntity<CardsDto> fetchCard(@RequestHeader("trustwave-correlation-id") String correlationId,
                                                  @RequestParam
                                                  @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                String mobileNumber) {
    logger.debug("fetchCardsDetails method start");
    CardsDto cardsDto = iCardsService.fetchCard(mobileNumber);
    logger.debug("fetchCardsDetails method end");
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardsDto);
  }

  @Operation(
          summary = "Update Card Details REST API",
          description = "REST API to update card details based on a card number"
  )
  @ApiResponses({
          @ApiResponse(
                  responseCode = "200",
                  description = "HTTP Status OK"
          ),
          @ApiResponse(
                  responseCode = "417",
                  description = "Expectation Failed"
          ),
          @ApiResponse(
                  responseCode = "500",
                  description = "HTTP Status Internal Server Error",
                  content = @Content(
                          schema = @Schema(implementation = ErrorResponseDto.class)
                  )
          )
  })
  @PutMapping("/update")
  public ResponseEntity<ResponseDto> updateCard(@Valid @RequestBody CardsDto cardsDto) {
    boolean isUpdated = iCardsService.updateCard(cardsDto);

    if (isUpdated) {
      return ResponseEntity
              .status(HttpStatus.OK)
              .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
    } else {
      return ResponseEntity
              .status(HttpStatus.EXPECTATION_FAILED)
              .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
    }
  }

  @Operation(
          summary = "Delete Card Details REST API",
          description = "REST API to delete Card details based on a mobile number"
  )
  @ApiResponses({
          @ApiResponse(
                  responseCode = "200",
                  description = "HTTP Status OK"
          ),
          @ApiResponse(
                  responseCode = "417",
                  description = "Expectation Failed"
          ),
          @ApiResponse(
                  responseCode = "500",
                  description = "HTTP Status Internal Server Error",
                  content = @Content(
                          schema = @Schema(implementation = ErrorResponseDto.class)
                  )
          )
  })
  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteCard(@RequestParam
                                                  @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                String mobileNumber) {
    boolean isDeleted = iCardsService.deleteCard(mobileNumber);

    if (isDeleted) {
      return ResponseEntity
              .status(HttpStatus.OK)
              .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
    } else {
      return ResponseEntity
              .status(HttpStatus.EXPECTATION_FAILED)
              .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
    }
  }

  @Operation(
          summary = "Get Build information",
          description = "Get Build information that is deployed into cards microservice"
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
  @GetMapping("/build-info")
  public ResponseEntity<String> getBuildInfo() {
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(buildVersion);
  }

  @Operation(
          summary = "Get Java version",
          description = "Get Java versions details that is installed into cards microservice"
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
  @GetMapping("/java-version")
  public ResponseEntity<String> getJavaVersion() {
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(environment.getProperty("java.home"));
  }

  @Operation(
          summary = "Get Contact Info",
          description = "Contact Info details that can be reached out in case of any issues"
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
  @GetMapping("/contact-info")
  public ResponseEntity<CardsContactInfoDto> getContactInfo() {
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardsContactInfoDto);
  }

}
