package com.example.backend.service;

import com.example.backend.model.Follow;
import com.example.backend.model.Usuario;
import com.example.backend.repository.FollowRepository;
import com.example.backend.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final UsuarioRepository usuarioRepository;

    public FollowService(FollowRepository followRepository, UsuarioRepository usuarioRepository) {
        this.followRepository = followRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void toggleFollow(Integer followerId, Integer followedId) {
        if (followerId.equals(followedId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User cannot follow themselves.");
        }

        Usuario follower = usuarioRepository.findById(followerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Follower user not found"));
        Usuario followed = usuarioRepository.findById(followedId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Followed user not found"));

        Optional<Follow> existingFollow = followRepository.findByFollowerAndFollowed(follower, followed);

        if (existingFollow.isPresent()) {
            followRepository.delete(existingFollow.get());
        } else {
            Follow newFollow = new Follow(follower, followed);
            followRepository.save(newFollow);
        }
    }

    public Long getFollowersCount(Integer userId) {
        Usuario user = usuarioRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return followRepository.countByFollowed(user);
    }

    public Long getFollowingCount(Integer userId) {
        Usuario user = usuarioRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return followRepository.countByFollower(user);
    }

    public boolean isFollowing(Integer followerId, Integer followedId) {
        Usuario follower = usuarioRepository.findById(followerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Follower user not found"));
        Usuario followed = usuarioRepository.findById(followedId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Followed user not found"));
        return followRepository.findByFollowerAndFollowed(follower, followed).isPresent();
    }
}