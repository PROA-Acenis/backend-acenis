package com.example.backend.repository;

import com.example.backend.model.Post;
import com.example.backend.dto.PostResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByAutor_IdUser(Integer autorId);

    @Query("SELECT new com.example.backend.dto.PostResponse(" +
            "p.id, " +
            "p.conteudo, " +
            "p.dataCriacao, " +
            "p.autor, " +
            "(SELECT COUNT(l) FROM Like l WHERE l.post.id = p.id), " +
            "(SELECT COUNT(c) FROM Comment c WHERE c.post.id = p.id), " +
            "EXISTS(SELECT l FROM Like l WHERE l.post.id = p.id AND l.user.idUser = :currentUserId)" +
            ") FROM Post p ORDER BY p.dataCriacao DESC")
    List<PostResponse> findAllPostsWithCounts(@Param("currentUserId") Integer currentUserId);

    @Query("SELECT new com.example.backend.dto.PostResponse(" +
            "p.id, " +
            "p.conteudo, " +
            "p.dataCriacao, " +
            "p.autor, " +
            "(SELECT COUNT(l) FROM Like l WHERE l.post.id = p.id), " +
            "(SELECT COUNT(c) FROM Comment c WHERE c.post.id = p.id), " +
            "EXISTS(SELECT l FROM Like l WHERE l.post.id = p.id AND l.user.idUser = :currentUserId)" +
            ") FROM Post p WHERE p.autor.idUser = :autorId ORDER BY p.dataCriacao DESC")
    List<PostResponse> findByAutor_IdUserWithCounts(@Param("autorId") Integer autorId, @Param("currentUserId") Integer currentUserId);
}