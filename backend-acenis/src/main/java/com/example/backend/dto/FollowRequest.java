package com.example.backend.dto;

public class FollowRequest {
    private Integer followerId;
    private Integer followedId;

    public FollowRequest() {
    }

    public FollowRequest(Integer followerId, Integer followedId) {
        this.followerId = followerId;
        this.followedId = followedId;
    }

    public Integer getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Integer followerId) {
        this.followerId = followerId;
    }

    public Integer getFollowedId() {
        return followedId;
    }

    public void setFollowedId(Integer followedId) {
        this.followedId = followedId;
    }
}