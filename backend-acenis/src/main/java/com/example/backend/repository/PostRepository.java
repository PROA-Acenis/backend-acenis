package com.example.backend.repository;

import com.example.redesocialteste.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAutorId(Long idUser);
}
