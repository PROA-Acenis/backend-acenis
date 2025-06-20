package com.example.backend.controller;

import com.example.backend.service.LikePostService;
import com.example.backend.dto.LikeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    private final LikePostService likePostService;

    public LikeController(LikePostService likePostService) {
        this.likePostService = likePostService;
    }

    @PostMapping("/toggle")
    public ResponseEntity<Void> toggleLike(@RequestBody LikeRequest likeRequest) {

        likePostService.toggleLike(likeRequest.getPostId(), likeRequest.getUserId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}