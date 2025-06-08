package com.fincodehub.controller;


import com.fincodehub.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author YangJian
 * @version 1.0.0
 * @title LikeController
 * @create 2025/6/8 10:12
 * @description <TODO description class purpose>
 */
@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    // 点赞操作
    @PostMapping("/like")
    public String like(@RequestParam String postId, @RequestParam String userId) {
        likeService.likePost(postId, userId);
        return "Liked post: " + postId + " by user: " + userId;
    }

    // 取消点赞
    @PostMapping("/unlike")
    public String unlike(@RequestParam String postId, @RequestParam String userId) {
        likeService.unlikePost(postId, userId);
        return "Unliked post: " + postId + " by user: " + userId;
    }

    // 获取点赞数
    @GetMapping("/count")
    public Long getLikeCount(@RequestParam String postId) {
        return likeService.getLikeCount(postId);
    }

    // 检查用户是否点赞
    @GetMapping("/status")
    public boolean hasLiked(@RequestParam String postId, @RequestParam String userId) {
        return likeService.hasLiked(postId, userId);
    }
}