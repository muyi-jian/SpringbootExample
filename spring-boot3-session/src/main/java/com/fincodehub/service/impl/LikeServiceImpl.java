package com.fincodehub.service.impl;


import com.fincodehub.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 点赞类实现类
 * @author YangJian
 * @version 1.0.0
 * @title LikeServiceImpl
 * @create 2025/6/8 10:08
 * @description <TODO description class purpose>
 */
@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    // Redis Key 前缀
    private static final String LIKE_KEY_PREFIX = "like:";

    // 用户点赞
    public void likePost(String postId, String userId) {
        String redisKey = LIKE_KEY_PREFIX + postId;
        redisTemplate.opsForSet().add(redisKey, userId);
    }

    // 用户取消点赞
    public void unlikePost(String postId, String userId) {
        String redisKey = LIKE_KEY_PREFIX + postId;
        redisTemplate.opsForSet().remove(redisKey, userId);
    }

    // 查询某个帖子点赞数
    public Long getLikeCount(String postId) {
        String redisKey = LIKE_KEY_PREFIX + postId;
        return redisTemplate.opsForSet().size(redisKey);
    }

    // 判断用户是否点赞
    public boolean hasLiked(String postId, String userId) {
        String redisKey = LIKE_KEY_PREFIX + postId;
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(redisKey, userId));
    }
}
