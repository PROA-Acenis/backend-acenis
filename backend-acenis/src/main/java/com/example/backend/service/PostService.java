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
        List<PostResponse> posts = postRepository.findAllPostsWithCounts();

        if (currentUserId != null) {
            Usuario currentUser = usuarioRepository.findById(currentUserId).orElse(null);
            if (currentUser != null) {
                final Usuario finalCurrentUser = currentUser;
                posts.forEach(postResponse -> {
                    postResponse.setLikedByUser(likePostRepository.findByPostIdAndUser(postResponse.getId(), finalCurrentUser).isPresent());
                });
            }
        }
        return posts;
    }

    public List<Post> getAllPostsRaw() {
        return postRepository.findAll();
    }


    public List<PostResponse> getPostsByAutorId(Integer autorId, Integer currentUserId) {
        List<PostResponse> posts = postRepository.findByAutor_IdUserWithCounts(autorId);

        if (currentUserId != null) {
            Usuario currentUser = usuarioRepository.findById(currentUserId).orElse(null);
            if (currentUser != null) {
                final Usuario finalCurrentUser = currentUser;
                posts.forEach(postResponse -> {
                    postResponse.setLikedByUser(likePostRepository.findByPostIdAndUser(postResponse.getId(), finalCurrentUser).isPresent());
                });
            }
        }
        return posts;
    }

    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return postRepository.existsById(id);
    }
}