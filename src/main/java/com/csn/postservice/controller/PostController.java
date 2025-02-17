package com.csn.postservice.controller;

import com.csn.postservice.dto.DetailedPostDto;
import com.csn.postservice.dto.PostDto;
import com.csn.postservice.dto.ResponseDto;
import com.csn.postservice.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD API for post service of CSN",
        description = "Create fetch delete for post details"
)
@RestController
@RequestMapping(path = "/api/v1",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Slf4j
public class PostController {
    private final PostService postService;

    @Operation(summary = "Create post REST API",
            description = "Creates new post with input of postDto"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @PostMapping
    public ResponseEntity<ResponseDto> createPost(@Valid @RequestBody PostDto postDto){
        postService.createPost(postDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201","Post created successfully"));
    }

    @Operation(summary = "Fetch post details REST API",
            description = "Fetches post details"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/{id}")
    public ResponseEntity<DetailedPostDto> fetchPost(@PathVariable("id") Long id,@RequestHeader("csn-correlation-id") String correlationId){
        log.debug("csn-correlation-id found: {}",correlationId);
        DetailedPostDto detailedPostDto = postService.fetchPost(id,correlationId);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(detailedPostDto);
    }

    @Operation(summary = "Delete post REST API",
            description = "Delete post with input of id"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"),
            @ApiResponse(
                    responseCode = "404",
                    description = "NOT FOUND"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deletePost(@PathVariable("id") Long id){
        postService.deletePost(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200","Post deleted successfully"));
    }
}
