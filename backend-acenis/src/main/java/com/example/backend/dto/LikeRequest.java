package com.example.backend.dto;

// Se estiver usando Lombok, adicione as importações e anotações necessárias
public class LikeRequest {
    private Integer postId;
    private Integer userId;

    public LikeRequest() {
    }

    public LikeRequest(Integer postId, Integer userId) {
        this.postId = postId;
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}