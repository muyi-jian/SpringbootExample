package com.fincodehub.controller;


/**
 * @author YangJian
 * @version 1.0.0
 * @title LeaderboardController
 * @create 2025/6/8 14:16
 * @description <TODO description class purpose>
 */

import com.fincodehub.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderboardService;

    // 添加用户得分
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestParam("userId") String userId, @RequestParam("score") double score) {
        leaderboardService.addUserToLeaderboard(userId, score);
        return ResponseEntity.ok("User added to leaderboard.");
    }

    // 获取前N名
    @GetMapping("/top")
    public ResponseEntity<Set<String>> getTopUsers(@RequestParam("topN") int topN) {
        return ResponseEntity.ok(leaderboardService.getTopUsers(topN));
    }

    // 获取某个用户的排名和得分
    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getUserRankAndScore(@PathVariable String userId) {
        return ResponseEntity.ok(leaderboardService.getUserRankAndScore(userId));
    }
}