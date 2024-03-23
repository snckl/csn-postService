package com.csn.postservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "Response",
        description = "Schema to hold successful response"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private String statusCode;
    private String statusMessage;
}
