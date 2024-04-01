package com.csn.postservice.service.client;

import com.csn.postservice.dto.ResponseDto;
import com.csn.postservice.dto.StorageDto;
import com.csn.postservice.service.feignFallback.StorageFeignClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@FeignClient(value = "storage-service",fallback = StorageFeignClientFallBack.class)
public interface StorageFeignClient {
    @PostMapping(value = "/api/v1/p/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> saveImage(@RequestPart("content") MultipartFile image, @PathVariable("id") Long id);

    @GetMapping(value = "/api/v1/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StorageDto> fetchImage(@PathVariable("id") Long id,@RequestHeader("csn-correlation-id") String correlationId);

    @DeleteMapping(value = "/api/v1/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> deleteImage(@PathVariable("id") Long id);
}
