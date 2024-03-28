package com.csn.postservice.service.feignFallback;

import com.csn.postservice.dto.ResponseDto;
import com.csn.postservice.dto.StorageDto;
import com.csn.postservice.service.client.StorageFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class StorageFeignClientFallBack implements StorageFeignClient {
    @Override
    public ResponseEntity<ResponseDto> saveImage(MultipartFile image, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<StorageDto> fetchImage(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto> deleteImage(Long id) {
        return null;
    }
}
