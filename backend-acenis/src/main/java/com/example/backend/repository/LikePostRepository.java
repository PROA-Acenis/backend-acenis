package com.example.backend.repository;

import com.example.backend.model.Like;
import com.example.backend.model.Post;
import com.example.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikePostRepository extends JpaRepository<Like, Integer> {

    Optional<Like> findByPostIdAndUser(Integer postId, Usuario user);

    Long countByPost_Id(Integer postId);
}