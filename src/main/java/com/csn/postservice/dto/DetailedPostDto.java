package com.csn.postservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(
        name="Post",
        description =  "Post schema which holds the post comment and storage information of the user"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailedPostDto {
    private String title;
    private String textContent;
    @Nullable
    private List<CommentDto> commentDto;
    @Nullable
    private StorageDto storageDto;
}
