package com.kenzie.appserver.config;

import com.kenzie.appserver.repositories.model.CustomerRecord;
import com.kenzie.appserver.service.model.Customer;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.cache.annotation.CacheEvict;

import java.util.concurrent.TimeUnit;

public class CacheStore {

    private Cache<String, Customer> cache;

    public CacheStore(int expire, TimeUnit timeUnit){
        // initalize cache
        this.cache = CacheBuilder.newBuilder()
                .expireAfterWrite(expire, timeUnit)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build();
    }

    public Customer get(String key){
        return cache.getIfPresent(key);
    }

    public void evict(String key) {
        // Invalidate/evict from cache
        if (key != null) {
            cache.invalidate(key);
        }
    }

    public void add(String key, Customer value) {
        // Add to cache
        this.cache.put(key, value);
    }
}
