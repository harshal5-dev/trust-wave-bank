package com.trustwave.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDto {

  @Schema(
          description = "Account Number of Trust Wave Bank account", example = "3454433243"
  )
  @NotEmpty(message = "Account number cannot be null or empty")
  @Pattern(regexp = "^\\d{10}$", message = "Account number should be 10 digits")
  private Long accountNumber;

  @Schema(
          description = "Account type of Trust Wave Bank account", example = "Savings"
  )
  @NotEmpty(message = "Account type cannot be null or empty")
  private String accountType;

  @Schema(
          description = "Trust Wave Bank branch address", example = "123 NewYork"
  )
  @NotEmpty(message = "Branch address cannot be null or empty")
  private String branchAddress;
}
