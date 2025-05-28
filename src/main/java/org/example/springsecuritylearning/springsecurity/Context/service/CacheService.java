package org.example.springsecuritylearning.springsecurity.Context.service;

import lombok.RequiredArgsConstructor;
import org.example.springsecuritylearning.springsecurity.Context.model.CachedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.net.CacheResponse;

@Service
@RequiredArgsConstructor

public class CacheService implements Serializable {
    @Autowired
    private final CacheManager cacheManager;

public CachedResponse get(String key){
    Cache cache=cacheManager.getCache(key);
    return cache!=null? cache.get(key,CachedResponse.class):null;




}
public void set(String key ,CachedResponse response){
    Cache cache=cacheManager.getCache(key);
    if (cache==null){
        cache.put(key ,response);

    }
}
public void clearAll(){
    cacheManager.getCacheNames().forEach(name-> cacheManager.getCache(name).clear());

}



}
