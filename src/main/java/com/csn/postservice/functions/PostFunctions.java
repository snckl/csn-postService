package com.csn.postservice.functions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class PostFunctions {

    @Bean
    public Consumer<String> isPostCreated(){
        return title -> {
            log.info("The post has created with title of " + title);
        };
    }
}
