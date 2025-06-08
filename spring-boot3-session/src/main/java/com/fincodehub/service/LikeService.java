package com.fincodehub.service;


/**
 * @author YangJian
 * @version 1.0.0
 * @title LikeService
 * @create 2025/6/8 10:07
 * @description <TODO description class purpose>
 */
public interface LikeService {
    // 用户点赞
    void likePost(String postId, String userId);

    // 用户取消点赞
    void unlikePost(String postId, String userId) ;

    // 查询某个帖子点赞数
    Long getLikeCount(String postId) ;

    // 判断用户是否点赞
    boolean hasLiked(String postId, String userId);
}
