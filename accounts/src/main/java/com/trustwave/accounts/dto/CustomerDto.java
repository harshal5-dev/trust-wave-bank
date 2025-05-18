package com.trustwave.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

  @Schema(
          description = "Name of the customer", example = "John Doe"
  )
  @NotEmpty(message = "Name cannot be null or empty")
  @Size(min = 5, max = 30, message = "customer name should be between 5 and 30 characters")
  private String fullName;

  @Schema(
          description = "Email address of the customer", example = "tutor@trustwave.com"
  )
  @NotEmpty(message = "Email cannot be null or empty")
  @Email(message = "Email should be valid")
  private String email;

  @Schema(
          description = "Mobile Number of the customer", example = "9345432123"
  )
  @Pattern(regexp = "^\\d{10}$", message = "Mobile number should be 10 digits")
  private String mobileNumber;

  @Schema(
          description = "Account details of the Customer"
  )
  private AccountsDto accountsDto;
}
