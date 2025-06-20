package com.example.backend.service;

import com.example.backend.model.Like;
import com.example.backend.model.Post;
import com.example.backend.model.Usuario;
import com.example.backend.repository.LikePostRepository;
import com.example.backend.repository.PostRepository;
import com.example.backend.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class LikePostService {

    private final LikePostRepository likePostRepository;
    private final PostRepository postRepository;
    private final UsuarioRepository usuarioRepository;

    public LikePostService(LikePostRepository likePostRepository, PostRepository postRepository, UsuarioRepository usuarioRepository) {
        this.likePostRepository = likePostRepository;
        this.postRepository = postRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void toggleLike(Integer postId, Integer userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
        Usuario user = usuarioRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Optional<Like> existingLike = likePostRepository.findByPostIdAndUser(postId, user);

        if (existingLike.isPresent()) {
            likePostRepository.delete(existingLike.get());
        } else {
            Like newLike = new Like();
            newLike.setPost(post);
            newLike.setUser(user);
            likePostRepository.save(newLike);
        }
    }
}