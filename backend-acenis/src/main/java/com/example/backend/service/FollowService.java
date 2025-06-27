package com.example.backend.service;

import com.example.backend.model.Follow;
import com.example.backend.model.Usuario;
import com.example.backend.repository.FollowRepository;
import com.example.backend.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final UsuarioRepository usuarioRepository;

    public FollowService(FollowRepository followRepository, UsuarioRepository usuarioRepository) {
        this.followRepository = followRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public boolean toggleFollow(Integer followerId, Integer followedId) {
        Optional<Follow> existingFollow = followRepository.findByFollower_IdUserAndFollowed_IdUser(followerId, followedId);

        if (existingFollow.isPresent()) {
            followRepository.delete(existingFollow.get());
            return false;
        } else {
            Usuario follower = usuarioRepository.findById(followerId)
                    .orElseThrow(() -> new RuntimeException("Seguidor não encontrado com id: " + followerId));
            Usuario followed = usuarioRepository.findById(followedId)
                    .orElseThrow(() -> new RuntimeException("Usuário a ser seguido não encontrado com id: " + followedId));

            Follow newFollow = new Follow();
            newFollow.setFollower(follower);
            newFollow.setFollowed(followed);
            newFollow.setFollowDate(LocalDateTime.now());
            followRepository.save(newFollow);
            return true;
        }
    }

    public Long getFollowersCount(Integer userId) {
        return followRepository.countByFollowed_IdUser(userId);
    }

    public Long getFollowingCount(Integer userId) {
        return followRepository.countByFollower_IdUser(userId);
    }

    public boolean isFollowing(Integer followerId, Integer followedId) {
        return followRepository.existsByFollower_IdUserAndFollowed_IdUser(followerId, followedId);
    }
}