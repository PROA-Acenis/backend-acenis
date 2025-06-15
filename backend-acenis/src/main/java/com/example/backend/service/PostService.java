package com.example.backend.service;

import com.example.backend.model.Post;
import com.example.backend.repository.PostRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
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