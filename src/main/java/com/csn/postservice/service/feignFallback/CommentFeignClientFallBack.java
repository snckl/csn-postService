package com.csn.postservice.service.feignFallback;

import com.csn.postservice.dto.CommentDto;
import com.csn.postservice.dto.ResponseDto;
import com.csn.postservice.service.client.CommentFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentFeignClientFallBack implements CommentFeignClient {
    @Override
    public ResponseEntity<List<CommentDto>> fetchComment(Long id,String correlationId) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto> createComment(CommentDto comment) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto> deleteAllComments(Long id) {
        return null;
    }
}
