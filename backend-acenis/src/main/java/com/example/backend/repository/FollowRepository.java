package com.example.backend.repository;

import com.example.backend.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {

    Optional<Follow> findByFollower_IdUserAndFollowed_IdUser(Integer followerId, Integer followedId);

    long countByFollowed_IdUser(Integer followedId);

    long countByFollower_IdUser(Integer followerId);

    boolean existsByFollower_IdUserAndFollowed_IdUser(Integer followerId, Integer followedId);
}