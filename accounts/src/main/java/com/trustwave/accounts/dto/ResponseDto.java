package com.trustwave.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
@NoArgsConstructor
public class ResponseDto {

  @Schema(
          description = "Status code in the response"
  )
  private String statusCode;

  @Schema(
          description = "Status message in the response"
  )
  private String statusMessage;
}
