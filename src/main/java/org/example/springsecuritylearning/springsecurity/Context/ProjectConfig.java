package org.example.springsecuritylearning.springsecurity.Context;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration

public class ProjectConfig {
    @Bean
    public CacheManager cacheManager(){
        return new ConcurrentMapCacheManager();
        //Temporary Default implementation


    }
}
