package com.csn.postservice.service;

import com.csn.postservice.dto.*;
import com.csn.postservice.entity.Post;
import com.csn.postservice.exception.MaxImageSizeExceededException;
import com.csn.postservice.exception.ResourceNotFoundException;
import com.csn.postservice.repository.PostRepository;
import com.csn.postservice.service.client.CommentFeignClient;
import com.csn.postservice.service.client.StorageFeignClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final CommentFeignClient commentFeignClient;
    private final StorageFeignClient storageFeignClient;


    public void createPost(PostDto postDto){
        Post post = Post.builder()
                .title(postDto.getTitle())
                .textContent(postDto.getTextContent())
                .build();
        Post createdPost = postRepository.save(post);
        log.info("Post created with id of {}",createdPost.getId());
    }

    public DetailedPostDto fetchPost(Long id){
        Optional<Post> post = postRepository.findById(id);

        if(post.isPresent()){
            DetailedPostDto detailedPostDto = DetailedPostDto.builder()
                    .title(post.get().getTitle())
                    .textContent(post.get().getTextContent()).build();

            StorageDto storageDto = storageFeignClient.fetchImage(id).getBody();
            List<CommentDto> commentDto = commentFeignClient.fetchComment(id).getBody();



            detailedPostDto.setCommentDto(commentDto);
            // detailedPostDto.setStorageDto(storageDto);

            return detailedPostDto;
        }

        throw new ResourceNotFoundException("Post","id",id.toString());
    }

    public void deletePost(Long id){
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()){
            postRepository.deleteById(id);
            log.info("Post deleted with id of {}",id);
        } else {
            throw new ResourceNotFoundException("post","id",id.toString());
        }
    }
}
