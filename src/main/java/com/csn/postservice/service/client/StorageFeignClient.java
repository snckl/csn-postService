package com.csn.postservice.service.client;

import com.csn.postservice.dto.ResponseDto;
import com.csn.postservice.dto.StorageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@FeignClient("storage-service")
public interface StorageFeignClient {
    @PostMapping(value = "/api/v1/storage/p/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> saveImage(@RequestParam("content") MultipartFile image, @PathVariable("id") Long id);

    @GetMapping(value = "/api/v1/storage/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StorageDto> fetchImage(@PathVariable("id") Long id);

    @DeleteMapping(value = "/api/v1/storage/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> deleteImage(@PathVariable("id") Long id);
}
