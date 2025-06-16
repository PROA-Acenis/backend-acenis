package com.example.backend.service;

import com.example.backend.model.Like;
import com.example.backend.model.Post;
import com.example.backend.model.Usuario;
import com.example.backend.repository.LikePostRepository;
import com.example.backend.repository.PostRepository;
import com.example.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import com.example.backend.dto.PostResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final LikePostRepository likePostRepository;
    private final UsuarioRepository usuarioRepository;

    public PostService(PostRepository postRepository, LikePostRepository likePostRepository, UsuarioRepository usuarioRepository) {
        this.postRepository = postRepository;
        this.likePostRepository = likePostRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public List<PostResponse> getAllPosts(Integer currentUserId) {
        List<Post> posts = postRepository.findAll();
        Usuario currentUser = null;
        if (currentUserId != null) {
            currentUser = usuarioRepository.findById(currentUserId).orElse(null);
        }

        final Usuario finalCurrentUser = currentUser;

        return posts.stream().map(post -> {
            Long likesCount = likePostRepository.countByPost(post);
            boolean likedByUser = false;
            if (finalCurrentUser != null) {
                likedByUser = likePostRepository.findByPostAndUser(post, finalCurrentUser).isPresent();
            }
            return new PostResponse(post, likesCount, likedByUser);
        }).collect(Collectors.toList());
    }

    public List<Post> getAllPostsRaw() {
        return postRepository.findAll();
    }


    public List<Post> getPostsByAutorId(Integer autorId) {
        return postRepository.findByAutor_IdUser(autorId);
    }

    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return postRepository.existsById(id);
    }
}