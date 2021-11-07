package com.example.zdnl7.config.system;

import com.example.zdnl7.entity.UserInfo;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.apache.catalina.User;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {
    /**
     * 配置默认的缓存管理器
     */
    @Primary
    @Bean("defaultCacheManager")
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                // 设置最后一次写入后经过固定时间过期.
                .expireAfterWrite(10, TimeUnit.SECONDS)
                // 初始的缓存空间大小
                .initialCapacity(100)
                // 缓存的最大条数
                .maximumSize(1000));
        return cacheManager;
    }

    /**
     * token放在本地缓存中
     * 可以改造为放在redis中
     */
    @Bean("tokenCacheManager")
    public Cache<String, UserInfo> caffeineCache() {
        return Caffeine.newBuilder()
                // 设置最后一次访问后经过固定时间过期.
                .expireAfterAccess(30L, TimeUnit.MINUTES)
                // 初始的缓存空间大小
                .initialCapacity(100)
                // 缓存的最大条数
                .maximumSize(10000)
                .build();
    }
}
