package com.csn.postservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name="Comment",
        description =  "Comment schema which holds the comment information of the user"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    @NotNull(message = "Post ID cannot be null")
    private Long postId;
    @NotBlank(message = "Comment cannot be blank")
    private String content;
}
