package com.csn.postservice.service.client;

import com.csn.postservice.dto.CommentDto;
import com.csn.postservice.dto.ResponseDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("comment-service")
public interface CommentFeignClient {

    @GetMapping(value = "/api/v1/comment/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommentDto>> fetchComment(@PathVariable("id") Long id);

    @PostMapping(value = "/api/v1/comment",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> createComment(@Valid @RequestBody CommentDto comment);

    @DeleteMapping(value = "/api/v1/comment/p/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> deleteAllComments(@PathVariable("id") Long id);
}
