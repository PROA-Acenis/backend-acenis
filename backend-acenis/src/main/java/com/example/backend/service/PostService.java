package com.example.backend.service;

import com.example.backend.model.Post;
import com.example.backend.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPostsByAutorId(Integer autorId) {
        // ALTERADO: Chama o método findByAutor_IdUser do repositório
        return postRepository.findByAutor_IdUser(autorId);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(Integer postId) {
        postRepository.deleteById(postId);
    }

    public boolean existsById(Integer postId) {
        return postRepository.existsById(postId);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
