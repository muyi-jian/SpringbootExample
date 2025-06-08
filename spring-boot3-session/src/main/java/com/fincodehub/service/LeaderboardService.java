package com.fincodehub.service;


import java.util.Map;
import java.util.Set;

/**
 * @author YangJian
 * @version 1.0.0
 * @title LeaderboardService
 * @create 2025/6/8 14:13
 * @description <TODO description class purpose>
 */
public interface LeaderboardService {

    // 添加用户及其得分到排行榜
    void addUserToLeaderboard(String userId, double score);

    // 获取前N名用户
    Set<String> getTopUsers(int n) ;

    // 获取某个用户的排名
    Long getUserRank(String userId) ;

    // 获取某个用户的得分
    Double getUserScore(String userId) ;

    // 获取用户的排名和得分
    Map<String, Object> getUserRankAndScore(String userId) ;
}
