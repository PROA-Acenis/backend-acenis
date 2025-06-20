package com.example.backend.controller;

import com.example.backend.service.LikePostService;
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
    public ResponseEntity<Void> toggleLike(@RequestParam Integer postId, @RequestParam Integer userId) {

        likePostService.toggleLike(postId, userId);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}