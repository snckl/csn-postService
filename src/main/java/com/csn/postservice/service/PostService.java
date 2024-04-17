package com.csn.postservice.service;

import com.csn.postservice.dto.*;
import com.csn.postservice.entity.Post;
import com.csn.postservice.exception.ResourceNotFoundException;
import com.csn.postservice.repository.PostRepository;
import com.csn.postservice.service.client.CommentFeignClient;
import com.csn.postservice.service.client.StorageFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    @Qualifier("com.csn.postservice.service.client.CommentFeignClient")
    private final CommentFeignClient commentFeignClient;
    @Qualifier("com.csn.postservice.service.client.StorageFeignClient")
    private final StorageFeignClient storageFeignClient;
    private final StreamBridge streamBridge;


    public void createPost(PostDto postDto){
        Post post = Post.builder()
                .title(postDto.getTitle())
                .textContent(postDto.getTextContent())
                .build();
        Post createdPost = postRepository.save(post);
        sendCommunication(post);
        log.info("Post created with id of {}",createdPost.getId());
    }

    public DetailedPostDto fetchPost(Long id,String correlationId){
        Optional<Post> post = postRepository.findById(id);

        if(post.isPresent()){
            DetailedPostDto detailedPostDto = DetailedPostDto.builder()
                    .title(post.get().getTitle())
                    .textContent(post.get().getTextContent()).build();

            ResponseEntity<StorageDto> storageDto = storageFeignClient.fetchImage(id,correlationId);
            ResponseEntity<List<CommentDto>> commentDto = commentFeignClient.fetchComment(id,correlationId);
            if(storageDto != null && storageDto.hasBody()){
                detailedPostDto.setStorageDto(storageDto.getBody());
            }
            if(commentDto != null && commentDto.hasBody()){
                detailedPostDto.setCommentDto(commentDto.getBody());
            }
            return detailedPostDto;
        }

        throw new ResourceNotFoundException("Post","id",id.toString());
    }

    public void deletePost(Long id){
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()){
            postRepository.deleteById(id);
            storageFeignClient.deleteImage(id);
            commentFeignClient.deleteAllComments(id);
            log.info("Post deleted with id of {}",id);
        } else {
            throw new ResourceNotFoundException("post","id",id.toString());
        }
    }

    private void sendCommunication(Post post){
        var postMessageDto = new PostMessageDto(post.getTitle(),post.getTextContent());
        log.info("Sending communication request for the details : {}",postMessageDto);
        var result =  streamBridge.send("sendCommunication-out-0",postMessageDto);
        log.info("Is the Communication request successfully processed: {}",result);
    }
}
