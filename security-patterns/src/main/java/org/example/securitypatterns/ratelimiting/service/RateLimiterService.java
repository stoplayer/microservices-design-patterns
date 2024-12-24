package org.example.securitypatterns.ratelimiting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class RateLimiterService {

    private static final int REQUEST_LIMIT = 5; // Limit of requests
    private static final int TIME_WINDOW = 60; // Time window in seconds

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public boolean isAllowed(String clientId) {
        String key = "rate_limit:" + clientId;

        Long currentRequests = redisTemplate.opsForValue().increment(key, 1);
        if (currentRequests == 1) {
            // Set expiration if this is the first request
            redisTemplate.expire(key, TIME_WINDOW, TimeUnit.SECONDS);
        }

        return currentRequests <= REQUEST_LIMIT;
    }
}
