package com.csn.postservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name="Storage",
        description =  "Storage schema which holds the image/video information of the user"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StorageDto {
    private Long postId;
    private String filename;
    private String type;
    private byte[] content;
}