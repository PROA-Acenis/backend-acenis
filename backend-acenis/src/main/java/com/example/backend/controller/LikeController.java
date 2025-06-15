package com.example.backend.controller;

import com.example.backend.service.LikePostService;
import com.example.backend.dto.LikeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private LikePostService likePostService;

    @PostMapping("/toggle")
    public ResponseEntity<String> toggleLike(@RequestBody LikeRequest likeRequest) {
        Integer postId = likeRequest.getPostId();
        Integer userId = likeRequest.getUserId();

        String message = likePostService.toggleLike(postId, userId);
        return ResponseEntity.ok(message);
    }
}
