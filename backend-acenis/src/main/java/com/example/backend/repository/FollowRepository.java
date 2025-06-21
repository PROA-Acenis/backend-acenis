package com.example.backend.repository;

import com.example.backend.model.Follow;
import com.example.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {

    Optional<Follow> findByFollowerAndFollowed(Usuario follower, Usuario followed);

    Long countByFollowed(Usuario followed);

    Long countByFollower(Usuario follower);

    Long countByFollowed_IdUser(Integer followedId); // Note o _IdUser

    boolean existsByFollower_IdUserAndFollowed_IdUser(Integer followerId, Integer followedId);
}