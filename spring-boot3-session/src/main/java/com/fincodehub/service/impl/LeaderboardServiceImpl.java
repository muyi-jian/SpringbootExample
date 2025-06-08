package com.fincodehub.service.impl;


import com.fincodehub.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 排行榜服务实现类
 * @author YangJian
 * @version 1.0.0
 * @title LeaderboardServiceImpl
 * @create 2025/6/8 14:13
 * @description <TODO description class purpose>
 */
@Service
public class LeaderboardServiceImpl implements LeaderboardService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String LEADERBOARD_KEY = "leaderboard";


    // 添加用户及其得分到排行榜
    public void addUserToLeaderboard(String userId, double score) {
        redisTemplate.opsForZSet().add(LEADERBOARD_KEY, userId, score);
    }

    // 获取前N名用户
    public Set<String> getTopUsers(int n) {
        return redisTemplate.opsForZSet().reverseRange(LEADERBOARD_KEY, 0, n - 1);
    }

    // 获取某个用户的排名
    public Long getUserRank(String userId) {
        return redisTemplate.opsForZSet().reverseRank(LEADERBOARD_KEY, userId);
    }

    // 获取某个用户的得分
    public Double getUserScore(String userId) {
        return redisTemplate.opsForZSet().score(LEADERBOARD_KEY, userId);
    }

    // 获取用户的排名和得分
    public Map<String, Object> getUserRankAndScore(String userId) {
        Map<String, Object> result = new HashMap<>();
        result.put("rank", getUserRank(userId));
        result.put("score", getUserScore(userId));
        return result;
    }
}
