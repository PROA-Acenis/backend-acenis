package com.example.backend.controller;

import com.example.backend.dto.PostRequest;
import com.example.backend.dto.PostResponse; // Importar PostResponse
import com.example.backend.model.Post;
import com.example.backend.model.Usuario;
import com.example.backend.repository.UsuarioRepository;
import com.example.backend.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final UsuarioRepository usuarioRepository;
    private final PostService postService;

    public PostController(UsuarioRepository usuarioRepository, PostService postService) {
        this.usuarioRepository = usuarioRepository;
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostRequest postRequest) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(postRequest.getIdUser());

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Usuario autor = usuarioOpt.get();

        Post newPost = new Post();
        newPost.setConteudo(postRequest.getConteudo());
        newPost.setAutor(autor);
        newPost.setDataCriacao(LocalDateTime.now());

        Post savedPost = postService.createPost(newPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts(@RequestParam(required = false) Integer userId) {
        List<PostResponse> posts = postService.getAllPosts(userId);
        return ResponseEntity.ok(posts);
    }


    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable Integer userId) {
        List<Post> posts = postService.getPostsByAutorId(userId);
        return ResponseEntity.ok(posts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        if (!postService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}