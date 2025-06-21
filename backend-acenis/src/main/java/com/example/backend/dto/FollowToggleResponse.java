package com.example.backend.dto;

public class FollowToggleResponse {
    private boolean isFollowing;
    private Long followedUserNewFollowersCount;
    private String message;

    public FollowToggleResponse(boolean isFollowing, Long followedUserNewFollowersCount, String message) {
        this.isFollowing = isFollowing;
        this.followedUserNewFollowersCount = followedUserNewFollowersCount;
        this.message = message;
    }

    public boolean getIsFollowing() {
        return isFollowing;
    }

    public void setIsFollowing(boolean isFollowing) {
        this.isFollowing = isFollowing;
    }

    public Long getFollowedUserNewFollowersCount() {
        return followedUserNewFollowersCount;
    }

    public void setFollowedUserNewFollowersCount(Long followedUserNewFollowersCount) {
        this.followedUserNewFollowersCount = followedUserNewFollowersCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}