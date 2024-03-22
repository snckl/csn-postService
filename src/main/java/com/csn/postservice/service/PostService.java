package com.csn.postservice.service;

import com.csn.postservice.dto.PostDto;
import com.csn.postservice.entity.Post;
import com.csn.postservice.exception.ResourceNotFoundException;
import com.csn.postservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void createPost(PostDto postDto){
        Post post = Post.builder()
                .title(postDto.getTitle())
                .textContent(postDto.getTextContent())
                .build();
        postRepository.save(post);
    }

    public PostDto fetchPost(Long id){
        Optional<Post> post = postRepository.findById(id);

        if(post.isPresent()){
            PostDto postDto = PostDto.builder()
                    .title(post.get().getTitle())
                    .textContent(post.get().getTextContent()).build();
            return postDto;
        }

        throw new ResourceNotFoundException("Post","id",id.toString());
    }

    public void deletePost(Long id){
         postRepository.deleteById(id);
    }
}
