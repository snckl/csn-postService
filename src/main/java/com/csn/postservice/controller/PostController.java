package com.csn.postservice.controller;

import com.csn.postservice.dto.PostDto;
import com.csn.postservice.dto.ResponseDto;
import com.csn.postservice.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/post",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class PostController {
    private final PostService postService;
    @PostMapping
    public ResponseEntity<ResponseDto> createPost(@Valid @RequestBody PostDto postDto){
        postService.createPost(postDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201","Post created successfully"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> fetchPost(@PathVariable("id") Long id){

        PostDto postDto = postService.fetchPost(id);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(postDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> deletePost(@PathVariable("id") Long id){
        postService.deletePost(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200","Post deleted successfully"));
    }
}
