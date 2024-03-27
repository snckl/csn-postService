package com.csn.postservice.service;

import com.csn.postservice.dto.PostDto;
import com.csn.postservice.entity.Post;
import com.csn.postservice.exception.ResourceNotFoundException;
import com.csn.postservice.repository.PostRepository;
import com.csn.postservice.service.client.CommentFeignClient;
import com.csn.postservice.service.client.StorageFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public PostDto fetchPost(Long id){
        Optional<Post> post = postRepository.findById(id);

        if(post.isPresent()){
            return PostDto.builder()
                    .title(post.get().getTitle())
                    .textContent(post.get().getTextContent()).build();
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
