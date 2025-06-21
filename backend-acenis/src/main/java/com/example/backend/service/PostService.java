package com.example.backend.service;

import com.example.backend.model.Like;
import com.example.backend.model.Post;
import com.example.backend.model.Usuario;
import com.example.backend.repository.LikePostRepository;
import com.example.backend.repository.PostRepository;
import com.example.backend.repository.UsuarioRepository;
import com.example.backend.repository.FollowRepository;
import org.springframework.stereotype.Service;
import com.example.backend.dto.PostResponse;
import com.example.backend.dto.AuthorResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final LikePostRepository likePostRepository;
    private final UsuarioRepository usuarioRepository;
    private final FollowRepository followRepository;

    public PostService(PostRepository postRepository, LikePostRepository likePostRepository, UsuarioRepository usuarioRepository, FollowRepository followRepository) {
        this.postRepository = postRepository;
        this.likePostRepository = likePostRepository;
        this.usuarioRepository = usuarioRepository;
        this.followRepository = followRepository;
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    private PostResponse enrichPostResponse(PostResponse postResponse, Integer currentUserId) {
        if (postResponse.getAutor() != null) {
            Long followersCount = followRepository.countByFollowed_IdUser(postResponse.getAutor().getId());
            postResponse.getAutor().setFollowersCount(followersCount);

            if (currentUserId != null) {
                boolean isFollowing = followRepository.existsByFollower_IdUserAndFollowed_IdUser(currentUserId, postResponse.getAutor().getId());
                postResponse.getAutor().setIsFollowingAuthor(isFollowing);
            } else {
                postResponse.getAutor().setIsFollowingAuthor(false);
            }
        }
        return postResponse;
    }

    public List<PostResponse> getAllPosts(Integer currentUserId) {
        List<PostResponse> posts = postRepository.findAllPostsWithCounts(currentUserId);

        return posts.stream()
                .map(postResponse -> enrichPostResponse(postResponse, currentUserId))
                .collect(Collectors.toList());
    }

    public List<Post> getAllPostsRaw() {
        return postRepository.findAll();
    }


    public List<PostResponse> getPostsByAutorId(Integer autorId, Integer currentUserId) {
        List<PostResponse> posts = postRepository.findByAutor_IdUserWithCounts(autorId, currentUserId);

        return posts.stream()
                .map(postResponse -> enrichPostResponse(postResponse, currentUserId))
                .collect(Collectors.toList());
    }

    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }

    public boolean existsById(Integer id) {
        return postRepository.existsById(id);
    }
}