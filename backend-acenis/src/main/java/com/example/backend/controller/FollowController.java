package com.example.backend.controller;

import com.example.backend.service.FollowService;
import com.example.backend.dto.FollowRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backend.dto.FollowToggleResponse;

@RestController
@RequestMapping("/api/follows")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }


    @PostMapping("/toggle")
    public ResponseEntity<FollowToggleResponse> toggleFollow(@RequestBody FollowRequest followRequest) {
        if (followRequest.getFollowerId() == null || followRequest.getFollowedId() == null) {
            return new ResponseEntity<>(new FollowToggleResponse(false, 0L, "Missing followerId or followedId"), HttpStatus.BAD_REQUEST);
        }

        boolean isNowFollowing = followService.toggleFollow(followRequest.getFollowerId(), followRequest.getFollowedId());
        Long newFollowersCount = followService.getFollowersCount(followRequest.getFollowedId());

        String message = isNowFollowing ? "Successfully followed user" : "Successfully unfollowed user";
        FollowToggleResponse response = new FollowToggleResponse(isNowFollowing, newFollowersCount, message);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<Long> getFollowersCount(@PathVariable Integer userId) {
        Long count = followService.getFollowersCount(userId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{userId}/following/count")
    public ResponseEntity<Long> getFollowingCount(@PathVariable Integer userId) {
        Long count = followService.getFollowingCount(userId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/isFollowing")
    public ResponseEntity<Boolean> isFollowing(
            @RequestParam Integer followerId,
            @RequestParam Integer followedId) {
        if (followerId == null || followedId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        boolean isFollowing = followService.isFollowing(followerId, followedId);
        return ResponseEntity.ok(isFollowing);
    }
}