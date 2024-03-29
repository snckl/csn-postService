package com.csn.postservice.service.client;

import com.csn.postservice.dto.CommentDto;
import com.csn.postservice.dto.ResponseDto;
import com.csn.postservice.service.feignFallback.CommentFeignClientFallBack;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "comment-service",fallback = CommentFeignClientFallBack.class)
public interface CommentFeignClient {

    @GetMapping(value = "/api/v1/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommentDto>> fetchComment(@PathVariable("id") Long id);

    @PostMapping(value = "/api/v1",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> createComment(@Valid @RequestBody CommentDto comment);

    @DeleteMapping(value = "/api/v1/p/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> deleteAllComments(@PathVariable("id") Long id);
}
