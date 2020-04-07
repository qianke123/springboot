package com.how2java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TagService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 插入元素左进右出
     * @param tag
     * @param fileCloudToken
     */
    public void pushTag(String tag, String fileCloudToken) {
        // 拼接tag的key值
        String tagKey = "tagCache:" + fileCloudToken;
        long num = redisTemplate.opsForList().remove(tagKey, 0, tag);
        redisTemplate.opsForList().leftPush(tagKey, tag);
        if (num < 1) {
            redisTemplate.opsForList().rightPop(tagKey);
        }
        // 重新设置key的过期时间
        redisTemplate.expire(tagKey, 30, TimeUnit.MINUTES);
    }

    /**
     * 获取tag列表
     * @param fileCloudToken
     * @return
     */
    public List<String> getTags(String fileCloudToken) {
        // 拼接tag的key值
        String tagkey = "tagCache:" + fileCloudToken;
        // 判断key值是否存在
        if (!redisTemplate.hasKey(tagkey)) {
            for (int i = 0; i < 7; i++) {
                redisTemplate.opsForList().leftPush(tagkey, "");
            }
        }
        // 重新设置key的过期时间
        redisTemplate.expire(tagkey, 30, TimeUnit.MINUTES);
        return redisTemplate.opsForList().range(tagkey, 0, -1);
    }

}
