package com.how2java.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class HelloRedis {
    @Autowired
    private RedisTemplate redisTemplate;

    public void hello() {

    }
    public static void main(String[] args) {
    }
}
