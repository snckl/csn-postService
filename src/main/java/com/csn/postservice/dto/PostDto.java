package com.csn.postservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    @NotBlank(message = "Post context should be provided")
    private String title;
    @NotBlank(message = "Post context should be provided")
    private String textContent;
}
