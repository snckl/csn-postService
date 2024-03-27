package com.csn.postservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(
        name="Post",
        description =  "Post schema which holds the post information of the user"
)
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
