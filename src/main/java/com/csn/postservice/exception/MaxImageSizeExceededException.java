package com.csn.postservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MaxImageSizeExceededException extends RuntimeException{
    public MaxImageSizeExceededException(String resourceName){
        super(String.format("%s is larger than 15MB",resourceName));
    }
}
